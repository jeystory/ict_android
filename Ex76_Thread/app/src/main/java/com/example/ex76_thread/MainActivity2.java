package com.example.ex76_thread;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex76_thread.R;

public class MainActivity2 extends AppCompatActivity {
    int value = 0 ;
    TextView textView1;
    Button button1;

    MainHandler handler ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThred bt = new BackgroundThred();
                bt.start();
            }
        });
        handler = new MainHandler();
    }

    class BackgroundThred extends Thread{
        @Override
        public void run() {
            for (int i=0; i<20; i++){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){}

                value += 1 ;
                Log.d("my","value : " + value) ;
                // 비 정상 종료 : 별도의 스레드에서는 메인스레드에서 만든 뷰에 접근할 수 없다.
                // 해결책 : Handler를 사용한다.
                //   textView1.setText("value값 : " + value);

                Message msg = handler.obtainMessage();
                // 정보를 보내고 받는 역할 한다.(intent 처럼)
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                msg.setData(bundle);

                handler.sendMessage(msg);
            }
        }
    }
    // 메인 스레드에서 만들었기 때문에  접근 가능
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // 보낸 정보를 받기
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            textView1.setText("value값 : " + value);
        }
    }
}