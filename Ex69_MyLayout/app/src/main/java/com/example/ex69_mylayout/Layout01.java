package com.example.ex69_mylayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout01 extends LinearLayout {
    ImageView imageView;
    TextView textView1, textView2;
    public Layout01(Context context) {
        super(context);
        init(context);
    }

    public Layout01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        //layout01.xml inflate
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout01, this, true);

        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);



    }

    //정보를 받아서 설정
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
    //
    public void setName(String name){
        textView1.setText(name);
    }
    public void setMobile(String mobile){
        textView2.setText(mobile);
    }


}
