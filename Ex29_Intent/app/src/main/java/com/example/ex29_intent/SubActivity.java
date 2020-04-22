package com.example.ex29_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 넘어온 인텐트 받기
        Intent r_intent = getIntent();

        String name = r_intent.getStringExtra("name");
        int age = r_intent.getIntExtra("age", 0);
        boolean gender = r_intent.getBooleanExtra("gender", false);

        //Toast.makeText(this, name + "\t"+ age + "\t"+ gender, Toast.LENGTH_SHORT).show();

        String[]  items = r_intent.getStringArrayExtra("items");
       // 여러개의 문자열을 하나로 합치기 위해서
       StringBuffer sb= new StringBuffer();
        for(String k:items){
            sb.append(k +"\n");
        }

        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }
}
