package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {
    Button btn1;
    ImageView imgView1;
    int idxBtn = 0;
    int[] images = {R.drawable.boy, R.drawable.coffe, R.drawable.dog, R.drawable.donald};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn1 = findViewById(R.id.btn1);


        imgView1 = findViewById(R.id.imgView1);
        imgView1.setImageResource(images[idxBtn++]);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView1.setImageResource(images[idxBtn++ % 4]);
            }
        });




    }
}
