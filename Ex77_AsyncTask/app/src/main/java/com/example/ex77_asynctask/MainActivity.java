package com.example.ex77_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //핸들러 사용
    Handler handler = new Handler();
    TextView textView1;
    Button button1, button2;
    ProgressBar progressBar1;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        progressBar1 = findViewById(R.id.progressBar1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (i = 0; i < progressBar1.getMax(); i++) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            //view에 데이터를 직접 저장하지 말고 handler 사용
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar1.setProgress(i);
                                    textView1.setText(progressBar1.getProgress() + "%");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}
