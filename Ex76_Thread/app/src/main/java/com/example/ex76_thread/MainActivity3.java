package com.example.ex76_thread;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex76_thread.R;

public class MainActivity3 extends AppCompatActivity {
    int value = 0 ;
    TextView textView1;
    Button button1;

    // 별도의 스레드가 메인스레등에 존재하는 뷰에 데이터를 넣기 위해서는
    // 반드시 handler 사용
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<20; i++){
                            try {
                                Thread.sleep(1000);
                            }catch (Exception e){
                            }
                            value += 1 ;
                            Log.d("my", "value : " +value);
                            // 비정상 종류
                            //  textView1.setText("value 값 : " + value);
                            // 해결이다.
                            /*
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                     textView1.setText("valeu 값 : "+value);
                                }
                            });
                            */

                            // 일정시간 지나서 실행한다.
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textView1.setText("valeu 값 : "+value);
                                }
                            }, 5000);


                        }
                    }
                }).start();
            }
        });
    }


}
