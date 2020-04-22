package com.example.ex69_mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Layout01 layout01;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);


        layout01 = findViewById(R.id.layout01);
        layout01.setName("김철수");
        layout01.setMobile("010-7979-9797");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout01.setImage(R.drawable.icecream);
                layout01.setName("안철수");
                layout01.setMobile("010-0000-9797");

            }
        });

    }
}
