package com.example.ex58_myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // 버튼을 눌렀을 때 서비스를 시작하자
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                //  인테트를 이용해서 서비스에게 정보를 보낸다.
                // Intent intent = new Intent(getApplicationContext(), MyService.class);
                Intent intent = new Intent(MainActivity.this, MyService.class);

                intent.putExtra("command","show");
                intent.putExtra("str", str);

                startService(intent);
            }
        });
        // 인텐트 받기 (필요없음)
        // Intent  intent = getIntent();
        //  processIntent(intent);
    }

    // MainActivity는 먼저 만들어져 있으므로 onCreate()를 다시 호출하지는 않는다.
    // 그러면 onNewIntent()를 오버라이딩해서 사용해야 한다.
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
