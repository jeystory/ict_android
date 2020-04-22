package com.example.ex11_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {
    Button button4;
    ProgressBar progressBar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressBar3 = findViewById(R.id.progressBar3);
        button4 = findViewById(R.id.button4);
        // thread 처리 전
        /*button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<=100;i++){
                    progressBar3.setProgress(i);
                    // 오류 발생
                    //SystemClock.sleep(1000);

                }
            }
        });*/

        // thread 처리
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 안드로이드에서 쓰레드 처리법
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<=100;i++) {
                            progressBar3.setProgress(i);
                            SystemClock.sleep(1000);
                        }
                    }
                }).start();
            }
        });
    }
}
