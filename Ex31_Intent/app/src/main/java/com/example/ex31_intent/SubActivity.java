package com.example.ex31_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    Button button2;
    TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);

        //메인에서 온 정보 받기
        Intent r_intent = getIntent();
        int su1 = r_intent.getIntExtra("su1", 0);
        int su2 = r_intent.getIntExtra("su2", 0);
        String op = r_intent.getStringExtra("op");
        int res =0;

        Intent s_intent = new Intent();

        if(su2 == 0 && op.equals("/")) {
            s_intent.putExtra("res", "not be divided by 0");
            setResult(RESULT_CANCELED, s_intent);
        } else {
            switch (op)
            {
                case "+": res = su1 + su2; break;
                case "-": res = su1 - su2; break;
                case "*": res = su1 * su2; break;
                case "/": res = su1 / su2; break;
                default:    break;
            }
            s_intent.putExtra("res", res);
            setResult(RESULT_OK, s_intent);
        }
        finish();

    }
}
