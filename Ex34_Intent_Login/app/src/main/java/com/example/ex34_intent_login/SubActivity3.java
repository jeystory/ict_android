package com.example.ex34_intent_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity3 extends AppCompatActivity {
    Button button6, button7;
    Intent rcvMainIntent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        button6 = findViewById(R.id.button6);
        button7  = findViewById(R.id.button7);

         rcvMainIntent2= getIntent();
        // 매출
        // 메인으로

       button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        //로그인으로
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sndMainIntent2 = new Intent(SubActivity3.this, MainActivity.class);
                sndMainIntent2.putExtra("res", "res2");
                startActivityForResult(sndMainIntent2, 130);
                finish();
            }
        });
    }
}
