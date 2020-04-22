package com.example.ex04_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final EditText editText, editText1, editText2;
        final TextView textCal, textRes;

        editText = findViewById(R.id.editText1);
        editText1 = findViewById(R.id.editText2);
        editText2 = findViewById(R.id.editText3);


        textCal = findViewById(R.id.textView4);
        textRes = findViewById(R.id.textView6);

        textCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = editText.getText().toString();
                String s2 = editText1.getText().toString();
                int k1 = Integer.parseInt(s1);
                int k2 = Integer.parseInt(s2);
                String op = editText2.getText().toString();
                int res = 0;
                switch(op){
                    case "+": res = k1+k2; break;
                    case "-": res = k1-k2;  break;
                    case "*": res = k1*k2;  break;
                    case "/": res = k1/k2;  break;
                    default:    break;
                }
                textRes.setText(String.valueOf(res));
            }
        });

    }
}
