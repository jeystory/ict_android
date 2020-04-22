package com.example.ex33_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1;
    ImageView[] imgView = new ImageView[9];
    Integer[] imgIdx = {R.id.imageView1, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9};
    int[] cntImg= new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        for(int i=0;i<imgView.length;i++)
        {
            imgView[i] = findViewById(imgIdx[i]);
            cntImg[i] = 0;
            final int idx=i;
            imgView[idx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cntImg[idx]++;
                }
            });
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity1.class);
                intent.putExtra("voting", cntImg);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100)
        {
            for(int i=0;i<9;i++)
            {
                cntImg[i] = 0;
            }
        }
    }
}
