package com.example.ex61_servicereceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    MyReceiver recevier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                //  인테트를 이용해서 서비스에게 정보를 보낸다.
                Intent s_intent = new Intent(MainActivity.this, MyService.class);
                s_intent.putExtra("str", str);

                startService(s_intent);
            }
        });

        recevier = new MyReceiver();
        IntentFilter filter= new IntentFilter();
        filter.addAction("show");
        registerReceiver(recevier, filter);
    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                String str = intent.getStringExtra("str");
                textView.setText("받는결과 " + str);
            }
        }
    }
}
