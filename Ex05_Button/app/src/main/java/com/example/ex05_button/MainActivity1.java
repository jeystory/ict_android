package com.example.ex05_button;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn1;
        ImageButton btn2;
        CheckBox chk1, chk2;
        RadioGroup rg1;
        final TextView result;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        rg1 = findViewById(R.id.rg1);
        result = findViewById(R.id.result);

        /* Button, ImageButton은 setOnClickListeners() */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("대한민국");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("KOREA");
            }
        });


        /* CheckBox 이벤트는 setOnCheckedChangeListener()*/
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // isChecked가 true면 check 된 상태, false이면 체크가 해제된 상태
                if(isChecked == true){
                    result.setTextColor(Color.GREEN);
                } else {
                    result.setTextColor(Color.GRAY);
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    result.setTextSize(100);
                } else {
                    result.setTextSize(50);
                }
            }
        });

        // RadioButton : setOnCheckedChangeListener
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 선택된 라디오 버튼의 id를 의미
                switch (checkedId){
                    case R.id.rb1: result.setTextColor(Color.RED);  break;
                    case R.id.rb2: result.setTextColor(Color.BLUE); break;
                }
            }
        });
    }
}
