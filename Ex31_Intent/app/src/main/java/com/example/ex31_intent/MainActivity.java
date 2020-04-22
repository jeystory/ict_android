package com.example.ex31_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<onActivityResult> extends AppCompatActivity {
    Button button1;
    EditText editText1, editText2;
    RadioGroup radioGroup1;
    String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        radioGroup1 = findViewById(R.id.rg1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int su1 = Integer.parseInt(editText1.getText().toString());
                    int su2 = Integer.parseInt(editText2.getText().toString());

                    switch (radioGroup1.getCheckedRadioButtonId()){
                        case R.id.rbtn1: op = "+";  break;
                        case R.id.rbtn2: op = "-";  break;
                        case R.id.rbtn3: op = "*";  break;
                        case R.id.rbtn4: op = "/";  break;
                        default:
                            Toast.makeText(MainActivity.this, "제대로 선택해 주세요", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    //화면 전환
                    // 정보를 보내고 결과 받기 : 쌍방향 인텐트
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("su1", su1);
                    intent.putExtra("su2", su2 );
                    intent.putExtra("op", op);

                    startActivityForResult(intent,100);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "제대로 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
            if(resultCode == RESULT_OK){    // 성공
                Toast.makeText(this, "\n 성공" + data.getIntExtra("res", 0), Toast.LENGTH_SHORT).show();
            } else if(resultCode == RESULT_CANCELED){   // 실패
                Toast.makeText(this, "\n 실패" + data.getStringExtra("res"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
