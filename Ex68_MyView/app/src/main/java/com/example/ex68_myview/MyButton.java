package com.example.ex68_myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;


import androidx.appcompat.widget.AppCompatButton;



// 자바 코딩으로 뷰(버튼을 생성)
public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs ) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context){
        setTextColor(Color.BLACK);
        // float textSize = 16.0f; // 16px

        // 사이즈를 sp로 지정하는 방법 (values-dimens.xml) 등록하고 호출하기
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Toast.makeText(getContext(), "onDraw호출", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "onTouch호출", Toast.LENGTH_SHORT).show();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);
                Toast.makeText(getContext(), "ACTION_DOWN 호출", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP :
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);
                Toast.makeText(getContext(), "ACTION_UP 호출", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
