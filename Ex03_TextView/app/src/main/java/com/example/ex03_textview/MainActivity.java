package com.example.ex03_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 현재 화면이 참조할 XML 파일을 지정해주는 메소드
        setContentView(R.layout.activity2_main);
        // findViewById : id를 이용해서 뷰를 찾는 메소드
        TextView tv1 = findViewById(R.id.tx1);
        // getter를 이용해서 정보를 가져옴
        String s1 = tv1.getText().toString();
        float s2 = tv1.getTextSize();
        // Toast를 이용해서 정보를 출력
        Toast.makeText(this, s1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "s2 =" + s2, Toast.LENGTH_SHORT).show();

        TextView tv2 =findViewById(R.id.tx2);
        // setter를 이용해서 원하는 데이터를 저장
        tv2.setText("대한민국");
        tv2.setTextSize(102);
        tv2.setTextColor(Color.GREEN);
    }
}
