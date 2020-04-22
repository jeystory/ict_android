package com.example.ex33_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class SubActivity1 extends AppCompatActivity {

    Button button2, button3;
    int[] cntVote = new int[9];
    RatingBar[] ratingBar =  new RatingBar[9];
    Integer[] rb_idx = { R.id.ratingBar1, R.id.ratingBar3, R.id.ratingBar3,
            R.id.ratingBar4, R.id.ratingBar5, R.id.ratingBar6,
            R.id.ratingBar7, R.id.ratingBar8, R.id.ratingBar9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);


        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        // 메인에서 온 이미지 클릭 횟수 받기
        //메인에서 온 정보 받기
        Intent r_intent = getIntent();
        cntVote =r_intent.getIntArrayExtra("voting");

        for(int i=0;i<9;i++){
           ratingBar[i] = findViewById(rb_idx[i]);

            final int idx = i;
            ratingBar[i].setRating(cntVote[idx]);
        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult(RESULT_CANCELED);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imgIdx = 0;

                for(int i = 0;i< cntVote.length-1;i++){
                        if(cntVote[i] < cntVote[i+1]) imgIdx = i+1;
                }

                Intent intent = new Intent(SubActivity1.this, SubActivity2.class);
                intent.putExtra("voting", imgIdx);
                startActivity(intent);
            }
        });
    }
}
