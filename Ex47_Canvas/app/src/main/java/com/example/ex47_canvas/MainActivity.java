package com.example.ex47_canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }

    private class MyCanvas extends View {
        Paint paint = new Paint();
        Path path = new Path();

        public MyCanvas(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);

            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);

            // 부드럽게 선 연결
            paint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 연결선  그리기
            canvas.drawPath(path,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int x = (int)event.getX();
            int y = (int)event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y); return true;

                case MotionEvent.ACTION_UP: break;

                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);   break;
            }
            invalidate(); // 초기화
            return true;
        }
    }
}
