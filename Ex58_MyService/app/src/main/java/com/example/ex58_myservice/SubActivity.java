package com.example.ex58_myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        // 서비스에서 돌아온 인텐트 받기 - 처음 실행되기 때문에 Create에서 실행
        Intent intent=getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }
    // 서비스에서 돌아온 인텐트 받기
    private void processIntent(Intent intent){
        if(intent != null){
            String command = intent.getStringExtra("command");
            String str = intent.getStringExtra("str");
            Toast.makeText(this, command + ",  "+ str, Toast.LENGTH_SHORT).show();
        }
    }
}
