package com.example.ex12_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {
    TextView textView1;
    Button button1;
    SeekBar seekBar1;
    int i;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        textView1 = findViewById(R.id.textView1);
        seekBar1 = findViewById(R.id.seekBar1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(i=0;i<seekBar1.getMax();i++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar1.setProgress(i);
                                    textView1.setText("진행률 " +  seekBar1.getProgress() + "%");
                                }
                           });

                            SystemClock.sleep(300);
                           i = seekBar1.getProgress();

                        }
                    }
                }).start();
            }
        });
    }
}
