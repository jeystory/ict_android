package com.example.ex34_intent_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity4 extends AppCompatActivity {
    Button button8, button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);

        button8 = findViewById(R.id.button8);
        button9  = findViewById(R.id.button9);

        Intent rcvMainIntent3 = getIntent();
        // 상품
        // 메인으로
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        //로그인으로
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sndMainIntent3 = new Intent(SubActivity4.this,MainActivity.class);
                sndMainIntent3.putExtra("res", "res3");
                startActivityForResult(sndMainIntent3, 140);
                finish();
            }
        });
    }
}
