package com.example.ex28_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//intent
// 1.명시적 인텐트 : 이동하고자 하는 곳을 명확하게 명시하는 것
// 2. 암시적 인텐트 : 이동하고 하는 곳이 안드로이드 기능이라서 명시할 필요가 없음.
// 화면전환 : Intent
// 1. 단순화면 전환 : 현재 화면에서 다른 화면으로 이동(activity->activity)
public class MainActivity extends AppCompatActivity {
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //명시적 인텐트 , 단순화면 전환
                // Intent intent = new Intent(현재위치, 이동 화면의 클래스);
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                //인텐트 실행
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //명시적 인텐트 , 단순화면 전환
                // Intent intent = new Intent(현재위치, 이동 화면의 클래스);
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                // 현재 인텐트는 기록에 남지 않는다(한번 화면을 보여주고 사라질때 완전히 없어진다.
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                //인텐트 실행
                startActivity(intent);
            }
        });
    }
}
