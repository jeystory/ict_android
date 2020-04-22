package com.example.ex46_canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView( new CanvasView(this));
    }

    private class CanvasView extends View {
        public CanvasView(Context context) {
            super(context);
            setBackgroundColor(Color.rgb(255,255,0));


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // canvas, paint
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
            paint.setStrokeCap(Paint.Cap.ROUND);

            // 선그리기 : drawLine(시작좌표, 끝좌표, paint
            canvas.drawLine(100, 100, 100, 200, paint);
            canvas.drawLine(100, 100, 200, 100, paint);
            paint.setColor(Color.BLUE);
            canvas.drawLine(200, 100, 200, 200, paint);
            canvas.drawLine(100, 200, 200, 200, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(100, 100, 200, 200, paint);
            canvas.drawLine(100, 200, 200, 100, paint);

            paint.setColor(Color.MAGENTA);
            // 사각형  : drawRect
            canvas.drawRect(300, 100, 400,200, paint);

            // 사각형 선으로 변경
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawRect(500, 100, 600, 200, paint);

            // 원 : drawCircle(중심좌표, 반지름, paint)

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(750, 150, 50, paint);

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.DKGRAY);
            canvas.drawCircle(950,150,50,paint);

            // 글자 : drawText // 글자 밑이 기준선이다.

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(100);
            canvas.drawText("Android",100, 300, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(200);
            canvas.drawText("Android",100, 600, paint);

            // 그림
            // 방법1 :
            BitmapDrawable bit = (BitmapDrawable)getResources().getDrawable(R.drawable.pi, null);
            Bitmap bitmap = bit.getBitmap();
            // 기본 크기
            canvas.drawBitmap(bitmap, 100,800, paint);
            // 크기 지정
            canvas.drawBitmap(bitmap, null, new RectF(500,900,1000,1400),paint);


            // 방법2
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.nougat);
            canvas.drawBitmap(bitmap1, null, new RectF(50,900,450,1400),paint);
        }
    }
}
