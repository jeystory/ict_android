package com.example.ex33_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SubActivity2 extends AppCompatActivity {
    ImageView imageView10;
    Button button4;
    int iIdx;
    int[] imgArray = {R.drawable.pic1, R.drawable.pic2,R.drawable.pic3,
            R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
            R.drawable.pic7,R.drawable.pic8, R.drawable.pic9,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        imageView10 = findViewById(R.id.imageView10);
        button4 = findViewById(R.id.button4);

        Intent r_intent = getIntent();
        iIdx =r_intent.getIntExtra("voting", 0);

        imageView10.setImageResource(imgArray[iIdx]);
        imageView10.setVisibility(View.VISIBLE);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sndIntent = new Intent(SubActivity2.this, MainActivity.class);
                sndIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(sndIntent);
            }
        });
    }
}
