package com.example.ex76_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int value = 0;
    TextView textView1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroudThread bt = new BackgroudThread();
                bt.start();
            }
        });


    }

    class BackgroudThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<20;i++){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}

                value += 1 ;
                Log.d("my","value : " + value) ;
                // 비 정상 종료 : 별도의 스레드에서는 메인스레드에서 만든 뷰에 접근할 수 없다.
                //   textView1.setText("value값 : " + value);
            }
        }
    }
}
