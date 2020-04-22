package com.example.ex11_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView textView1, textView2;
    ProgressBar progressBar1, progressBar2;
    Button button1;
    // 스레드에서 뷰을 수정, 변경할 때 사용
    Handler handler = new Handler();
    int i, j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 스레드 처리시 주의 사항 : 뷰를 직접 수정할 수 없다.
                // 핸들러를 이용해서 수정해야 한다.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (i=0; i<= progressBar1.getMax(); i++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar1.setProgress(i);
                                    textView1.setText(progressBar1.getProgress()+ "%");
                                }
                            });
                            SystemClock.sleep((int)(Math.random()*500));
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (j=0; j<= progressBar2.getMax(); j++){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar2.setProgress(j);
                                    textView2.setText(progressBar2.getProgress()+ "%");
                                }
                            });
                            SystemClock.sleep((int)(Math.random()*500));
                        }
                    }
                }).start();
            }
        });


    }
}
