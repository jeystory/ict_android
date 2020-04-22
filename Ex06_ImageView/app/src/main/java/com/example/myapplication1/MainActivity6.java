package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn1, btn2, btn3, btn4;
        final ImageView imgView6, imgView7,imgView8,imgView9;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        imgView6 = findViewById(R.id.imageView6);
        imgView7 = findViewById(R.id.imageView7);
        imgView8 = findViewById(R.id.imageView8);
        imgView9 = findViewById(R.id.imageView9);

        imgView6.setVisibility(View.VISIBLE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView6.setVisibility(View.VISIBLE);
                imgView7.setVisibility(View.INVISIBLE);
                imgView8.setVisibility(View.INVISIBLE);
                imgView9.setVisibility(View.INVISIBLE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView6.setVisibility(View.INVISIBLE);
                imgView7.setVisibility(View.VISIBLE);
                imgView8.setVisibility(View.INVISIBLE);
                imgView9.setVisibility(View.INVISIBLE);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView6.setVisibility(View.INVISIBLE);
                imgView7.setVisibility(View.INVISIBLE);
                imgView8.setVisibility(View.VISIBLE);
                imgView9.setVisibility(View.INVISIBLE);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView6.setVisibility(View.INVISIBLE);
                imgView7.setVisibility(View.INVISIBLE);
                imgView8.setVisibility(View.INVISIBLE);
                imgView9.setVisibility(View.VISIBLE);
            }
        });
    }
}
