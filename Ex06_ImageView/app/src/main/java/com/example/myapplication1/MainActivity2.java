package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn1, btn2,btn3, btn4;
        final ImageView imgView1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1= findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        imgView1 = findViewById(R.id.imgView1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView1.setImageResource(R.drawable.boy);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView1.setImageResource(R.drawable.coffe);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView1.setImageResource(R.drawable.dog);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView1.setImageResource(R.drawable.donald);
            }
        });



    }
}
