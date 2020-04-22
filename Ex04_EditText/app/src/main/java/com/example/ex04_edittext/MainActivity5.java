package com.example.ex04_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    TextView textView1, textView2;
    EditText editText1, editText2, editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText1 = findViewById(R.id.editText1);
        editText2= findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String s1 = editText1.getText().toString();
                    String s2 = editText2.getText().toString();
                    int k1 = Integer.parseInt(s1);
                    int k2 = Integer.parseInt(s2);
                    String op = editText3.getText().toString();
                    int res = 0;
                    switch(op){
                        case "+": res = k1+k2; break;
                        case "-": res = k1-k2;  break;
                        case "*": res = k1*k2;  break;
                        case "/": res = k1/k2;  break;
                        default:    break;
                    }
                    editText1.setText(""); // 입력란 비우기
                    editText2.setText("");
                    editText3.setText("");
                    editText1.requestFocus();  // 커서 처음으로 옮겨주기

                    textView2.setText(String.valueOf(res));

                } catch(Exception e) {
                    Toast.makeText(MainActivity5.this, "제대로 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
