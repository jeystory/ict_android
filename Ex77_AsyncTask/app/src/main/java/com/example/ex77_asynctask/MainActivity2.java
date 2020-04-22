package com.example.ex77_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    Handler handler = new Handler();
    TextView textView1;
    Button button1, button2;
    ProgressBar progressBar1;

    //AsyncTask 사용
    int value;
    BackgroundTask task;

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
                task = new BackgroundTask();
                task.execute(); // 실행 메소드
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);  //취소 메소드
            }
        });
    }

    //AsyncTask  클래스를 상속받아서 사용(쓰레드를 위한 코드와 UI 접근 코드를 해당 클래스에 한번에 작성할 수 있음
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        // 실행전
        @Override
        protected void onPreExecute() {
            value = 0;
            progressBar1.setProgress(value);
        }

        //백그라운드에서 실행
        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false){
                value++;
                if(value >= 100){
                    break;

                }else{
                    publishProgress(value);
                }

                try {
                    Thread.sleep(500);
                }catch (Exception e){}
            }
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar1.setProgress(values[0].intValue());
            textView1.setText(values[0].intValue() +"%");
        }

        // 쓰레드가 끝난 후 처리
        @Override
        protected void onPostExecute(Integer integer) {
            progressBar1.setProgress(0);
            textView1.setText("0%");
        }

        @Override
        protected void onCancelled() {
            // 쓰레드 실행 도중 취소할 때
            progressBar1.setProgress(0);
            textView1.setText("0%");
        }
    }
}
