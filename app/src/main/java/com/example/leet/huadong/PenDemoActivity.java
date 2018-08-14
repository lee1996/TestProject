package com.example.leet.huadong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.onyx.android.sdk.api.device.epd.EpdController;
import com.onyx.android.sdk.pen.BrushRender;
import com.onyx.android.sdk.pen.RawInputCallback;
import com.onyx.android.sdk.pen.TouchHelper;
import com.onyx.android.sdk.pen.data.TouchPoint;
import com.onyx.android.sdk.pen.data.TouchPointList;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by leet on 18-7-9.
 */

public class PenDemoActivity extends AppCompatActivity {
    private static final String TAG = PenDemoActivity.class.getSimpleName();
    /** skip point count*/
    private static final int INTERVAL = 10;

    @BindView(R.id.button_pen)
    Button buttonPen;
    @BindView(R.id.button_eraser)
    Button buttonEraser;
    @BindView(R.id.surfaceview)
    SurfaceView surfaceView;
    @BindView(R.id.cb_render)
    CheckBox cbRender;
    @BindView(R.id.rb_brush)
    RadioButton rbBrush;
    @BindView(R.id.rb_pencil)
    RadioButton rbPencil;

    private TouchHelper touchHelper;

    private Paint paint = new Paint();
    private TouchPoint startPoint;
    private int countRec = 0;

    private Bitmap bitmap;
    private Canvas canvas;

    private final float STROKE_WIDTH = 3.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pen);

        ButterKnife.bind(this);
        initPaint();
        initSurfaceView();
        touchHelper.setRawDrawingEnabled(true);
    }

    @Override
    protected void onResume() {
        touchHelper.setRawDrawingEnabled(true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        touchHelper.setRawDrawingEnabled(false);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        touchHelper.closeRawDrawing();
        if (bitmap !=null) {
            bitmap.recycle();
            bitmap = null;
        }
        super.onDestroy();
    }

    private void initPaint(){
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(STROKE_WIDTH);
    }

    private void initSurfaceView() {
        touchHelper = TouchHelper.create(surfaceView, callback);

        surfaceView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int
                    oldRight, int oldBottom) {
                if (cleanSurfaceView()){
                    surfaceView.removeOnLayoutChangeListener(this);
                }
                List<Rect> exclude = new ArrayList<>();
                exclude.add(getRelativeRect(surfaceView, buttonEraser));
                exclude.add(getRelativeRect(surfaceView, buttonPen));
                exclude.add(getRelativeRect(surfaceView, cbRender));
                exclude.add(getRelativeRect(surfaceView, rbBrush));
                exclude.add(getRelativeRect(surfaceView, rbPencil));

                Rect limit = new Rect();
                surfaceView.getLocalVisibleRect(limit);
                touchHelper.setStrokeWidth(STROKE_WIDTH)
                        .setLimitRect(limit, exclude)
                        .openRawDrawing();
                touchHelper.setStrokeStyle(TouchHelper.STROKE_STYLE_BRUSH);
            }
        });
        touchHelper.setRawDrawingEnabled(true);
        touchHelper.setStrokeStyle(TouchHelper.STROKE_STYLE_PENCIL);
    }

    @OnClick(R.id.button_pen)
    public void onPenClick() {
        touchHelper.setRawDrawingEnabled(true);
        onRenderEnableClick();
    }

    @OnClick(R.id.button_eraser)
    public void onEraserClick() {
        touchHelper.setRawDrawingEnabled(false);
        if (bitmap !=null) {
            bitmap.recycle();
            bitmap = null;
        }
        cleanSurfaceView();
    }

    @OnCheckedChanged(R.id.cb_render)
    public void onRenderEnableClick() {
        touchHelper.setRawDrawingRenderEnabled(cbRender.isChecked());
        if (bitmap !=null) {
            bitmap.recycle();
            bitmap = null;
        }
        Log.d(TAG,"onRenderEnableClick setRawDrawingRenderEnabled =  " + cbRender.isChecked());
    }

    @OnClick({R.id.rb_brush, R.id.rb_pencil})
    public void onRadioButtonClicked(RadioButton radioButton) {

        boolean checked = radioButton.isChecked();
        Log.d(TAG, radioButton.toString());
        switch (radioButton.getId()) {
            case R.id.rb_brush:
                if (checked) {
                    touchHelper.setStrokeStyle(TouchHelper.STROKE_STYLE_BRUSH);
                    Log.d(TAG, "STROKE_STYLE_BRUSH");
                }
                break;
            case R.id.rb_pencil:
                if (checked) {
                    touchHelper.setStrokeStyle(TouchHelper.STROKE_STYLE_PENCIL);
                    Log.d(TAG, "STROKE_STYLE_PENCIL");
                }
                break;
        }
        // refresh ui
        onEraserClick();
        onPenClick();
    }

    public Rect getRelativeRect(final View parentView, final View childView) {
        int [] parent = new int[2];
        int [] child = new int[2];
        parentView.getLocationOnScreen(parent);
        childView.getLocationOnScreen(child);
        Rect rect = new Rect();
        childView.getLocalVisibleRect(rect);
        rect.offset(child[0] - parent[0], child[1] - parent[1]);
        return rect;
    }

    private boolean cleanSurfaceView() {
        if (surfaceView.getHolder() == null) {
            return false;
        }
        Canvas canvas = surfaceView.getHolder().lockCanvas();
        if (canvas == null) {
            return false;
        }
        canvas.drawColor(Color.WHITE);
        surfaceView.getHolder().unlockCanvasAndPost(canvas);
        return true;
    }

    private void drawRect(TouchPoint endPoint){
        Canvas canvas = surfaceView.getHolder().lockCanvas();
        if (canvas == null ) {
            return;
        }

        if (startPoint == null || endPoint == null) {
            surfaceView.getHolder().unlockCanvasAndPost(canvas);
            return;
        }

        canvas.drawColor(Color.WHITE);
        canvas.drawRect(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), paint);
        Log.d(TAG,"drawRect ");
        surfaceView.getHolder().unlockCanvasAndPost(canvas);
    }

    private RawInputCallback callback = new RawInputCallback() {

        @Override
        public void onBeginRawDrawing(boolean b, TouchPoint touchPoint) {
            Log.d(TAG, "onBeginRawDrawing");
            startPoint = touchPoint;
            Log.d(TAG,touchPoint.getX() +", " +touchPoint.getY());
            countRec = 0;
            disableHandTouch();
        }

        @Override
        public void onEndRawDrawing(boolean b, TouchPoint touchPoint) {
            Log.d(TAG, "onEndRawDrawing");
            if (!cbRender.isChecked()){
                drawRect(touchPoint);
            }
            Log.d(TAG,touchPoint.getX() +", " +touchPoint.getY());
            enableHandTouch();
            drawBitmapToSurface();
        }

        @Override
        public void onRawDrawingTouchPointMoveReceived(TouchPoint touchPoint) {
            Log.d(TAG, "onRawDrawingTouchPointMoveReceived");
            Log.d(TAG,touchPoint.getX() +", " +touchPoint.getY());
            countRec++;
            countRec = countRec % INTERVAL;
            if (!cbRender.isChecked() && countRec == INTERVAL - 1 ){
                drawRect(touchPoint);
            }
            Log.d(TAG,"countRec = " + countRec);
        }

        @Override
        public void onRawDrawingTouchPointListReceived(TouchPointList touchPointList) {
            Log.d(TAG, "onRawDrawingTouchPointListReceived");
            drawScribbleToBitmap(touchPointList.getPoints());
        }

        @Override
        public void onBeginRawErasing(boolean b, TouchPoint touchPoint) {
            Log.d(TAG, "onBeginRawErasing");
        }

        @Override
        public void onEndRawErasing(boolean b, TouchPoint touchPoint) {
            Log.d(TAG, "onEndRawErasing");
        }

        @Override
        public void onRawErasingTouchPointMoveReceived(TouchPoint touchPoint) {
            Log.d(TAG, "onRawErasingTouchPointMoveReceived");
        }

        @Override
        public void onRawErasingTouchPointListReceived(TouchPointList touchPointList) {
            Log.d(TAG, "onRawErasingTouchPointListReceived");
        }
    };

    private void disableHandTouch() {
        boolean isIgnoreHandTouch = EpdController.isTouchAreaIgnoreRegionDetect(this);
        if (!isIgnoreHandTouch) {
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            Rect rect = new Rect(0, 0, width, height);
            Rect[] arrayRect =new Rect[]{rect};
            EpdController.setTouchAreaIgnoreRegion(this, arrayRect);
            EpdController.setTouchAreaIgnoreRegionDetectStatus(this, true);
        }
    }

    private void enableHandTouch() {
        boolean isIgnoreHandTouch = EpdController.isTouchAreaIgnoreRegionDetect(this);
        if (isIgnoreHandTouch) {
            EpdController.resetTouchAreaIgnoreRegion(this);
        }

    }

    private void drawScribbleToBitmap(List<TouchPoint> list) {
        if (!cbRender.isChecked()) {
            return;
        }
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
        }

        if (rbBrush.isChecked()) {
            float maxPressure = EpdController.getMaxTouchPressure();
            BrushRender.drawStroke(canvas, paint, list, STROKE_WIDTH, maxPressure);
        }

        if (rbPencil.isChecked()) {
            Path path = new Path();
            PointF prePoint = new PointF(list.get(0).x, list.get(0).y);
            path.moveTo(prePoint.x, prePoint.y);
            for (TouchPoint point : list) {
                path.quadTo(prePoint.x, prePoint.y, point.x, point.y);
                prePoint.x = point.x;
                prePoint.y = point.y;
            }
            canvas.drawPath(path, paint);
        }
    }

    private void drawBitmapToSurface() {
        if (!cbRender.isChecked()) {
            return;
        }
        if (bitmap == null) {
            return;
        }
        Canvas lockCanvas= surfaceView.getHolder().lockCanvas();
        if (lockCanvas == null) {
            return;
        }
        lockCanvas.drawColor(Color.WHITE);
        lockCanvas.drawBitmap(bitmap, 0f, 0f, paint);
        surfaceView.getHolder().unlockCanvasAndPost(lockCanvas);
        // refresh ui
        touchHelper.setRawDrawingEnabled(false);
        touchHelper.setRawDrawingEnabled(true);
        if (!cbRender.isChecked()) {
            touchHelper.setRawDrawingRenderEnabled(false);
        }
    }
}
