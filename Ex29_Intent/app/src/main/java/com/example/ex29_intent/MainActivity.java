package com.example.ex29_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화면 전환
                //2.데이터를 가지고 화면전환
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                //데이터 저장 -> 부가데이터 (Extra Data)
                intent.putExtra("name", "hong");
                intent.putExtra("age", 19);
                intent.putExtra("gender", true);

                String[] items = {"Doolli","1998", "true"};
                intent.putExtra("items",items);

                startActivity(intent);


            }
        });
    }
}
