package com.example.ex13_ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity1 extends AppCompatActivity {
    RatingBar ratingBar1, ratingBar2, ratingBar3,ratingBar4, ratingBar5;
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        button1 = findViewById(R.id.button1);
        button2= findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar1.setRating(ratingBar1.getRating()+ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()+ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()+ratingBar3.getStepSize());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar1.setRating(ratingBar1.getRating()-ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()-ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()-ratingBar3.getStepSize());
            }
        });

        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar5.setRating(rating);
            }
        });
    }
}
