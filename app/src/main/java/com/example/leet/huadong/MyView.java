package com.example.leet.huadong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by leet on 18-7-10.
 */

public class MyView extends View {
    Paint paint=new Paint();

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(6.0f);
        paint.setAntiAlias(true);

        Drawable drawable1=this.getResources().getDrawable(R.drawable.kuang);
        BitmapDrawable drawable2= (BitmapDrawable) drawable1;
        Bitmap bitmap= drawable2.getBitmap();
        int w=drawable1.getIntrinsicWidth();
        int h=drawable1.getIntrinsicHeight();
        float scalex=(float)(w+300)/w;
        float scaleY=(float)(h+300)/h;
        Matrix matrix=new Matrix();
        matrix.postScale(scalex,scaleY);
        Bitmap newB=Bitmap.createBitmap(bitmap,0,0,w,h,matrix,true);


//        canvas.drawBitmap(newB,0,0,paint);
        canvas.drawCircle(100,100,80,paint);
        canvas.drawLine(12,23,123,234,paint);
        canvas.drawRect(250, 75, 1650, 1220,paint);
    }
}
