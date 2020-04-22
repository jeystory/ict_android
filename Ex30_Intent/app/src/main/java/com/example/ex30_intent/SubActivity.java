package com.example.ex30_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    Button button2;
    TextView textView2;
    int su1, su2, res;
    String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        button2 = findViewById(R.id.button2);
        textView2 = findViewById(R.id.textView2);

        //메인에서 온 정보 받기
        Intent r_intent = getIntent();
        su1 = r_intent.getIntExtra("su1", 0);
        su2 = r_intent.getIntExtra("su2", 0);
        op = r_intent.getStringExtra("op");

        textView2.setText("\n su1="+su1+"\n su2="+su2+"\n op="+op);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 계산 결과값 반환
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
        });
    }

}
