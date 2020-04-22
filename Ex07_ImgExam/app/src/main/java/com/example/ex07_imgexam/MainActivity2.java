package com.example.ex07_imgexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    ImageView imgView2, imgView3;
    Button btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn2= findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        imgView2 = findViewById(R.id.imgView2);
        imgView3 = findViewById(R.id.imgView3);

        //imgView2.setVisibility(View.INVISIBLE);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*   방법 1
                imgView2.setImageResource(R.drawable.boy);
                imgView2.setVisibility(View.VISIBLE);
                imgView3.setVisibility(View.INVISIBLE);*/
                //방법 2
                imgView2.setImageResource(R.drawable.donald);
                imgView2.setVisibility(View.VISIBLE);
                imgView3.setImageResource(0);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 방법 1
               imgView3.setImageResource(R.drawable.boy);
                imgView3.setVisibility(View.VISIBLE);
                imgView2.setVisibility(View.INVISIBLE);
                */
                // 방법 2
                imgView3.setImageResource(R.drawable.donald);
                imgView3.setVisibility(View.VISIBLE);
                imgView2.setImageResource(0);

            }
        });
    }
}
