package com.example.ex28_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    Button button3, button4, button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인에서 intent를 MainActivity가 하나 더 생긴다.
                //명시적 인텐트 , 단순화면 전환
                // Intent intent = new Intent(현재위치, 이동 화면의 클래스);
                //Intent intent = new Intent(SubActivity.this, MainActivity.class);
                //인텐트 실행
                //startActivity(intent);

                //현재 화면을 삭제하면 이전 화면인 MainActivity 가 나옴
                finish();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인에서 intent를 MainActivity가 하나 더 생긴다.
                //명시적 인텐트 , 단순화면 전환
                // Intent intent = new Intent(현재위치, 이동 화면의 클래스);
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                //flag : 현재 인텐트가 맨 위에 위치하고 다른 액티비티들은 모두 삭제(보통 홈버튼에서 사용)
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

                //인텐트 실행
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인에서 intent를 MainActivity가 하나 더 생긴다.
                //명시적 인텐트 , 단순화면 전환
                // Intent intent = new Intent(현재위치, 이동 화면의 클래스);
                Intent intent = new Intent(SubActivity.this,SubActivity.class);
                //flag : FLAG_ACTIVITY_SINGLE_TOP 맨 위에 위치하면 재귀호출할때 새로 만들어지지 않음
               intent.setFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);

                //인텐트 실행
                startActivity(intent);
            }
        });
    }
}
