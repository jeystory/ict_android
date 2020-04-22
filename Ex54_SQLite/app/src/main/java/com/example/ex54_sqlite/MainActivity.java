package com.example.ex54_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        // 일정추가(오늘일정 추가)
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 오늘을 구하지 않고 그냥 넘기기
                Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intent);
            }
        });
        // 일정보기(오늘일정 보기)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 오늘 정보를 구해서 넘기기
                Intent intent = new Intent(MainActivity.this, Sub2Activity.class);
                // 오늘 날짜 정보 구해서 인텐트에 이용해서 넘기기
                // 오늘 날짜
                int y= Calendar.getInstance().get(Calendar.YEAR);
                int m = Calendar.getInstance().get(Calendar.MONTH);
                int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                String nalja = y+"_"+(m+1)+"_"+d ;
                intent.putExtra("nalja", nalja);
                startActivity(intent);
            }
        });
        // 전체일정
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sub3Activity.class);
                startActivity(intent);
            }
        });
        // 종료
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
