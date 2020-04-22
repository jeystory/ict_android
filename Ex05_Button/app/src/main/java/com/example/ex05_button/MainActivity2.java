package com.example.ex05_button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity2 extends AppCompatActivity {

    ToggleButton tbtn1;
    Switch switch1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tbtn1 = findViewById(R.id.tbtn1);
        switch1 = findViewById(R.id.switch1);
        textView1 = findViewById(R.id.textView1);

        // 토글 버튼 , 스위치는 체크박스 같은 이벤트가 발생
        tbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 체크박스와 마찬가지로 선택되면 true 해제되면 false
                if(isChecked){
                    textView1.setText("대한민국");
                    textView1.setTextColor(Color.rgb(255,0,0));
                }else{
                    textView1.setText("KOREA");
                    textView1.setTextColor(Color.rgb(0,0,255));
                }
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textView1.setText("대한민국 서울");
                    textView1.setTextColor(Color.rgb(255,255,0));
                }else{
                    textView1.setText("KOREA SEOUL");
                    textView1.setTextColor(Color.rgb(0,255,255));
                }
            }
        });
    }
}
