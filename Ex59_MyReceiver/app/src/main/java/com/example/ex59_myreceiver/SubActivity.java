package com.example.ex59_myreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        //리시버에서 호출한 액티비티가 메모리에 존재하지 않는 액티비티이므로 onCreate()에서 실행
        Intent intent = getIntent();
        processIntent(intent);
        Log.d("my", "Sub_onCreate()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
        Log.d("my", "Sub_onNewIntent()");
    }

    private  void  processIntent(Intent intent){
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");

            textView1.setText(sender);
            textView2.setText(contents);
        }
    }
}
