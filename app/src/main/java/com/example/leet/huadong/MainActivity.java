package com.example.leet.huadong;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuInflater;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioButton rbt3;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private ImageView image;
    private Button bump;
    private SurfaceView surfaceView;
    private LinearLayout linearLayout;
    private TextView textView;
    private TextView question_title;
    private LinearLayout timu;

    private RadioButton a;
    private RadioButton b;
    private RadioButton c;
    private RadioButton d;
    private RadioButton e;
    private RadioButton f;
    private RadioButton g;
    private RadioButton h;
    private RadioButton i;
    private RadioButton j;
    private RadioButton k;
    private RadioButton l;
    private RadioButton m;
    private RadioButton n;
    private RadioButton o;
    private RadioButton p;
    private RadioButton q;
    private WebView webview_a;
    private WebView webview_b;
    private WebView webview_c;
    private WebView webview_d;
    private WebView webview_e;
    private WebView webview_f;
    private WebView webview_g;
    private WebView webview_h;
    private WebView webview_i;
    private WebView webview_j;
    private WebView webview_k;
    private WebView webview_l;
    private WebView webview_m;
    private WebView webview_n;
    private WebView webview_o;
    private WebView webview_p;
    private WebView webview_q;
    //private String IMAGE_URL="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530615659563&di=566b6cf9ac219754a0c5725927bac5bc&imgtype=0&src=http%3A%2F%2Fwww.wallcoo.com%2Fnature%2FApple_OSX_Mountain_Lion_Secret_Wallpapers%2Fwallpapers%2F3200x2000%2FCosmos02.jpg";
    private String IMAGE_URL="http://p7fb60490.bkt.clouddn.com/FjnfamsUnedeU48VgQFZJg92djJF";
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what==1){
//                Drawable drawable= (Drawable) msg.obj;
//                Log.i("drawable"," "+msg.what+"  msg obj "+msg.obj.toString());
//                rbt3.setCompoundDrawables(drawable,null,null,null);
//            }
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup group=findViewById(R.id.rg);
        RadioButton rbt1=findViewById(R.id.rbt1);
        RadioButton rbt2=findViewById(R.id.rbt2);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        d=findViewById(R.id.d);
        e=findViewById(R.id.e);
        f=findViewById(R.id.f);
        g=findViewById(R.id.g);
        h=findViewById(R.id.h);
        i=findViewById(R.id.i);
        j=findViewById(R.id.j);
        k=findViewById(R.id.k);
        l=findViewById(R.id.l);
        m=findViewById(R.id.m);
        n=findViewById(R.id.n);
        o=findViewById(R.id.o);
        p=findViewById(R.id.p);
        q=findViewById(R.id.q);


        bump=findViewById(R.id.bump);
        image=findViewById(R.id.image);
        rbt3=findViewById(R.id.rbt3);
        surfaceView=findViewById(R.id.surfaceview);
        linearLayout=findViewById(R.id.layout);
        textView=findViewById(R.id.test);
        question_title=findViewById(R.id.question_title);
        timu=findViewById(R.id.timu);
        webview_a=findViewById(R.id.webview_a);
        webview_b=findViewById(R.id.webview_b);
        webview_c=findViewById(R.id.webview_c);
        webview_d=findViewById(R.id.webview_d);
        webview_e=findViewById(R.id.webview_e);
        webview_f=findViewById(R.id.webview_f);
        webview_g=findViewById(R.id.webview_g);
        webview_h=findViewById(R.id.webview_h);
        webview_i=findViewById(R.id.webview_i);
        webview_j=findViewById(R.id.webview_j);
        webview_k=findViewById(R.id.webview_k);
        webview_l=findViewById(R.id.webview_l);
        webview_m=findViewById(R.id.webview_m);
        webview_n=findViewById(R.id.webview_n);
        webview_o=findViewById(R.id.webview_o);
        webview_p=findViewById(R.id.webview_p);
        webview_q=findViewById(R.id.webview_q);


        btn1=findViewById(R.id.dialog1);
        btn2=findViewById(R.id.dialog2);
        btn3=findViewById(R.id.dialog3);
        btn4=findViewById(R.id.dialog4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TestPopActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.app.AlertDialog.THEME_TRADITIONAL);
                builder.setTitle("清空确认");
                builder.setMessage("点击确认将会删除整道题目的所有答案，确认删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setTitle("清空确认");
                builder.setMessage("点击确认将会删除整道题目的所有答案，确认删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
                builder.setTitle("清空确认");
                builder.setMessage("点击确认将会删除整道题目的所有答案，确认删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        webview_a.loadDataWithBaseURL(null,"这是a选项","text/html", "utf-8", null);
        setWebView(webview_a);
        webview_b.loadDataWithBaseURL(null,"这是b选项","text/html", "utf-8", null);
        setWebView(webview_b);
        webview_c.loadDataWithBaseURL(null,"这是c选项","text/html", "utf-8", null);
        setWebView(webview_c);
        webview_d.loadDataWithBaseURL(null,"这是d选项","text/html", "utf-8", null);
        setWebView(webview_d);
        webview_e.loadDataWithBaseURL(null,"这是e选项","text/html", "utf-8", null);
        setWebView(webview_e);
        webview_f.loadDataWithBaseURL(null,"这是f选项","text/html", "utf-8", null);
        setWebView(webview_f);
        webview_g.loadDataWithBaseURL(null,"这是g选项","text/html", "utf-8", null);
        setWebView(webview_g);
        webview_h.loadDataWithBaseURL(null,"这是h选项","text/html", "utf-8", null);
        setWebView(webview_h);
        webview_i.loadDataWithBaseURL(null,"这是i选项","text/html", "utf-8", null);
        setWebView(webview_i);
        webview_j.loadDataWithBaseURL(null,"这是j选项","text/html", "utf-8", null);
        setWebView(webview_j);
        webview_k.loadDataWithBaseURL(null,"这是k选项","text/html", "utf-8", null);
        setWebView(webview_k);
        webview_l.loadDataWithBaseURL(null,"这是l选项","text/html", "utf-8", null);
        setWebView(webview_l);
        webview_m.loadDataWithBaseURL(null,"这是m选项","text/html", "utf-8", null);
        setWebView(webview_m);
        webview_n.loadDataWithBaseURL(null,"这是n选项","text/html", "utf-8", null);
        setWebView(webview_n);
        webview_o.loadDataWithBaseURL(null,"这是o选项","text/html", "utf-8", null);
        setWebView(webview_o);
        webview_p.loadDataWithBaseURL(null,"这是p选项","text/html", "utf-8", null);
        setWebView(webview_p);
        webview_q.loadDataWithBaseURL(null,"这是q选项","text/html", "utf-8", null);
        setWebView(webview_q);


//        setWebView(webview_b);
//        setWebView(webview_c);
//        setWebView(webview_d);
//        setWebView(webview_e);
//        setWebView(webview_f);
//        setWebView(webview_g);
//        setWebView(webview_h);
//        setWebView(webview_i);
//        setWebView(webview_j);
//        setWebView(webview_k);
//        setWebView(webview_l);
//        setWebView(webview_m);
//        setWebView(webview_n);
//        setWebView(webview_o);
//        setWebView(webview_p);
//        setWebView(webview_q);

        String str="123,,123,,123,,";
        String[] result=str.split(",,");
        for(int i=0;i<result.length;i++)
            Log.i("数组分开后"," 第 "+i+" 个元素是 "+result[i]);
            //I/机型:  NoteS
            // I/机型:  Note
        Log.i("机型"," "+ Build.MODEL);

        String test="12345";
        Log.i("子字符串"," "+test.substring(0,test.length()-1));
//        Log.i("webview的宽高"," 宽是 "+webview_a.getLayoutParams().width+" 高是 "+webview_a.getLayoutParams().height);
        //给textview设置填充字符
        String text="老夫聊发少年狂，左牵黄，右擎苍，锦帽貂裘，千骑卷平冈。为报倾城随太守，亲射虎，看孙郎。酒酣胸胆尚开张。鬓微霜，又何妨！持节云中，何日遣冯唐？会挽雕弓如满月，西北望，射天狼";
        textView.setText(Html.fromHtml(text));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        timu.measure(0,0);
        question_title.setText(Html.fromHtml(text));
        question_title.setMovementMethod(LinkMovementMethod.getInstance());

        Drawable drawable=getResources().getDrawable(R.drawable.kuang);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        rbt1.setCompoundDrawables(drawable,null,null,null);
        Log.i("huadong","1");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.i("tag","1");
//                    Drawable drawable1=Drawable.createFromStream(new URL(IMAGE_URL).openStream(),"");
//                    Log.i("tag","3");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        List<Student> list=new ArrayList<>();
        Student student=new Student();
        list.add(student);
        student.name=12;
        student.age=23;
        Log.i("list","list "+list.get(0).name+" list "+list.get(0).age);

//
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                final Drawable drawable = loadImageFromNetwork(IMAGE_URL);
//                rbt3.post(new Runnable(){
//                    @Override
//                    public void run() {
//                        //这句话最关键
//                        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
//                        // TODO Auto-generated method stub
//                        rbt3.setCompoundDrawables(drawable,null,null,null);
//
//                    }}) ;
//            }
//
//        }).start();
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
        //image.setImageBitmap(newB);
//        Bitmap bitmap1=Bitmap.createBitmap(newB.getWidth(),newB.getHeight(), Bitmap.Config.ARGB_8888);
//        Log.i("bitmap"," h is "+newB.getHeight()+" w is "+newB.getWidth());
//        Canvas canvas=new Canvas(bitmap1);
//        canvas.drawColor(Color.BLUE);
//        canvas.drawARGB(255,255,0,0);
//        Paint paint=new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(6.0f);
//        paint.setAntiAlias(true);
//        //canvas.drawBitmap(newB,0,0,paint);
//
//        canvas.drawCircle(100,100,80,paint);
//        canvas.drawLine(12,23,345,567,paint);

        bump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PenDemoActivity.class);
                startActivity(intent);
            }
        });

//        MyView myView=new MyView(this);
//        myView.setMinimumHeight(500);
//        myView.setMinimumWidth(1000);
//        myView.invalidate();
//        linearLayout.addView(myView);



    }

    public void setWebView(WebView myWebview){

        WebSettings webSettings = myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= 11) {
            myWebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } else {
            myWebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        // Force links and redirects to open in the WebView instead of in a browser
//        myWebview.setWebViewClient(new WebViewClient());
    }

    private float getCharacterWidth(String text, float size) {
        if (null == text || "".equals(text))
            return 0;
        Paint paint = new Paint();
        paint.setTextSize(size);
        return paint.measureText(text);
    }

    private Drawable loadImageFromNetwork(String imageUrl){
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            Log.i("huadong","3");
            drawable = Drawable.createFromStream(new URL(imageUrl).openStream(),"image.jpg");
            Log.i("huadong","5");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }

    class Student{
        int name;
        int age;
        public Student(){

        }
        public Student(int name,int age){
            this.age=age;
            this.name=name;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
