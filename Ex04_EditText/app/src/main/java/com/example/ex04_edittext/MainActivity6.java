package com.example.ex04_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    TextView tView1, tView2;
    EditText eText1, eText2, eText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        tView1 = findViewById(R.id.tView1);
        tView2= findViewById(R.id.tView2);

        eText1 = findViewById(R.id.eText1);
        eText2 = findViewById(R.id.eText2);
        eText3 = findViewById(R.id.eText3);

        tView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String s1 = eText1.getText().toString();
                    String s2 = eText2.getText().toString();
                    int k1 = Integer.parseInt(s1);
                    int k2 = Integer.parseInt(s2);
                    String op = eText3.getText().toString();
                    int res = 0;
                    switch(op){
                        case "+": res = k1+k2; break;
                        case "-": res = k1-k2;  break;
                        case "*": res = k1*k2;  break;
                        case "/": res = k1/k2;  break;
                        default:    break;
                    }
                    eText1.setText(""); // 입력란 비우기
                    eText2.setText("");
                    eText3.setText("");
                    eText1.requestFocus();  // 커서 처음으로 옮겨주기

                    tView2.setText(String.valueOf(res));

                } catch(Exception e) {
                    Toast.makeText(MainActivity6.this, "제대로 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
