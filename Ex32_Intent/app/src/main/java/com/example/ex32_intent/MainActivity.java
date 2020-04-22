package com.example.ex32_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                String msg = textView1.getText().toString();
                intent.putExtra("msg", msg);

                //결과는 onActivityResult()로 전송 받는다
                startActivityForResult(intent,200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 200){
            if(resultCode==RESULT_OK) {
                textView1.setText(data.getStringExtra("res"));
            } else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "작업이 취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
