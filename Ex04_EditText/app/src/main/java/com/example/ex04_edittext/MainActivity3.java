package com.example.ex04_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final EditText editText13 = findViewById(R.id.editText13);
        final EditText editText14 = findViewById(R.id.editText14);
        final EditText editText15 = findViewById(R.id.editText15);
        final EditText editText16 = findViewById(R.id.editText16);

        final TextView textView1 = findViewById((R.id.textView1));
        final TextView textView2 = findViewById((R.id.textView2));
        TextView textView3 = findViewById((R.id.textView3));

        TextView textView7 = findViewById((R.id.textView7));

        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String s1 = editText13.getText().toString();
                    String s2 = editText14.getText().toString();
                    int k1 = Integer.parseInt(s1);
                    int k2 = Integer.parseInt(s2);
                    String op = editText16.getText().toString();
                    int res = 0;
                    switch(op){
                        case "+": res = k1+k2; break;
                        case "-": res = k1-k2;  break;
                        case "*": res = k1*k2;  break;
                        case "/": res = k1/k2;  break;
                        default:    break;
                    }
                    editText13.setText(""); // 입력란 비우기
                    editText14.setText("");
                    editText15.setText("");
                    editText13.requestFocus();  // 커서 처음으로 옮겨주기

                    editText15.setText(String.valueOf(res));

                } catch(Exception e) {
                    Toast.makeText(MainActivity3.this, "제대로 입력하세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
