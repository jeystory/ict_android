package com.example.ex32_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    EditText editText1;
    Button button2, button3;
    String main_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        button2 = findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        editText1 = findViewById(R.id.editText1);

        //메인에서 온 정보 받기
        Intent r_intent = getIntent();
        main_msg = r_intent.getStringExtra("msg");
        Toast.makeText(this, main_msg, Toast.LENGTH_SHORT).show();
        editText1.setText(main_msg);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s_intent = new Intent();
                main_msg = editText1.getText().toString();
                s_intent.putExtra("res", main_msg);
                setResult(RESULT_OK, s_intent);
                finish();
            }
        });

    }
}
