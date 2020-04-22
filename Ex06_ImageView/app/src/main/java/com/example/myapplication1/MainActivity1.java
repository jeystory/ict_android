package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imageView1, imageView2, imageView3, imageView4;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        // 크기 지정
        imageView1.getLayoutParams().height = 400;
        imageView1.getLayoutParams().width = 400;
        imageView1.setScaleType(ImageView.ScaleType.CENTER);

        // visibility : gone, invisible
        imageView3.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.VISIBLE);

        // 이미지 소스를 지정해서 보여주기 - 이미지뷰에 이미지 넣기
        // 1. 방법
        //imageView2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.coffe));
        //2.방법
        imageView2.setImageResource(R.drawable.coffe);



    }
}
