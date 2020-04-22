package com.example.ex08_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1, imageView2 ;
    Button button1, button2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 방법1
               //  imageView1.setVisibility(View.VISIBLE);
               //  imageView2.setVisibility(View.INVISIBLE);

                // 방법2 ; 이미지뷰의 크기를 지정하고, 둘 다 속성은  VISIBLE
                imageView1.setImageResource(R.drawable.dream02);
                imageView2.setImageResource(0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // imageView1.setVisibility(View.INVISIBLE);
                // imageView2.setVisibility(View.VISIBLE);

                imageView1.setImageResource(0);
                imageView2.setImageResource(R.drawable.dream02);
            }
        });
    }
}
