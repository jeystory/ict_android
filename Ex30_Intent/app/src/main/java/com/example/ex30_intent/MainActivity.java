package com.example.ex30_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        textView1 = findViewById(R.id.textView1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //화면 전환
                // 3. 정보를 보내고 결과 받기 : 쌍방향 인텐트
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("su1", 10);
                intent.putExtra("su2", 0);
                intent.putExtra("op", "/");

                //쌍방향 인텐트는 보낼때 requestCode를 함께 전송
                // 나중에 결과를 받을 때 requestCode를 가지고 확인해서 받음.
                // 보내는 메소드도 다름
                // intent를 보내는 메소드
                //startAcitivity(intent)
                //인텐트를 보내고 나중에 결과를 받겠다는 의미
                //결과는 onActivityResult()로 전송 받는다
                startActivityForResult(intent,100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        requestCode : 전송할 때 요청 코드
        resultCode : 전송 받은 결과를 가지고 있는 코드(성공또는 실패를 가짐)
                    응답 결과가 실패해도 결과를 반환 받음
         date : Sub에서 보내온 intent를 의미
        */

        if(requestCode == 100){
            if(resultCode == RESULT_OK){    // 성공
                textView1.append("\n 성공" + data.getIntExtra("res", 0));
            } else if(resultCode == RESULT_CANCELED){   // 실패
                textView1.append("\n 실패" + data.getStringExtra("res"));
            }
        }


    }
}
