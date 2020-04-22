package com.example.ex49_canvas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }

    private class MyCanvas extends View
    {
        Bitmap bitmap;
        int x=0,y=0,nx=20, ny = 20;

        public MyCanvas(Context context) {
            super(context);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cupcake);
            handler.sendEmptyMessageDelayed(0,200);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if(x<0 || x> (canvas.getWidth() - 150)){ nx = -nx; }
            if(y<0 || y > (canvas.getHeight() - 150)){  ny = -ny; }

            x = x + nx;
            y = y + ny;

            canvas.drawBitmap(bitmap, null, new RectF(x,y,x+150, y+150), null);
        }

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                invalidate();
                // 0 => requestCode,     200 => 지연시간
                handler.sendEmptyMessageDelayed(0,200);
            }
        };
    }
}
