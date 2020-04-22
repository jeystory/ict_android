package com.example.ex04_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    EditText editText10, editText11,editText12;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);
        editText12 = findViewById(R.id.editText12);
        textView1 = findViewById(R.id.textView1);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText12.setText(editText10.getText().toString());
                String s1 = editText10.getText().toString();
                String s2 = editText11.getText().toString();
                int k1 = Integer.parseInt(s1);
                int k2 = Integer.parseInt(s2);
                editText12.setText(" 결과는: " + (k1+k2));
            }
        });
    }
}
