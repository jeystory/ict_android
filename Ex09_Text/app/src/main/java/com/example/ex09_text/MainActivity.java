package com.example.ex09_text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1 ;
    TextView textView1;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String msg = editText1.getText().toString();
                Toast.makeText(MainActivity.this, msg , Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });

        // EditText 이벤트
        editText1.addTextChangedListener(new TextWatcher() {
            // 변경전
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // s => 문자열
                // byte[] b = s.toString().getBytes();
                // int sCount = b.length ;
                // textView1.setText( sCount+ " / 80 바이트" );

                char[] c = s.toString().toCharArray();
                int sCount = c.length;
                textView1.setText( sCount+ " / 80 문자" );
            }
            // 변경후
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
