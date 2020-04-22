package com.example.ex73_animation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4, button5;
    ImageView imageView1, imageView2;
    //  animation list
    AnimationDrawable drawable;

    // 트윈 애니메이션 : res - anim - 폴더 만들어서 xml 소스를 사용해서 애니메이션 작업
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);

        drawable = (AnimationDrawable)imageView2.getDrawable();

        //drawable.start();

      button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.start();
            }
        });

      imageView2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              drawable.stop();
              if(drawable.getCurrent() == drawable.getFrame(0)){
                  Toast.makeText(MainActivity.this, "가위", Toast.LENGTH_SHORT).show();
              } else if(drawable.getCurrent() == drawable.getFrame(1)) {
                  Toast.makeText(MainActivity.this, "바위", Toast.LENGTH_SHORT).show();
              } else if(drawable.getCurrent() == drawable.getFrame(2)) {
                  Toast.makeText(MainActivity.this, "보", Toast.LENGTH_SHORT).show();
              }
          }
      });

      button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
              imageView1.startAnimation(animation);
          }
      });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale2);
                imageView1.startAnimation(animation);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                imageView1.startAnimation(animation);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
                imageView1.startAnimation(animation);
            }
        });
    }

    /* @Override
    protected void onResume() {
        super.onResume();
        drawable.start();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawable.stop();
    }
}
