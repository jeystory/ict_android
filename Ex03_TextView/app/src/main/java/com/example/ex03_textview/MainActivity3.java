package com.example.ex03_textview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    TextView tvA, tvB, tvC, tvD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        /* 참조할 객체를 참조변수에 저장 */
        tvA = findViewById(R.id.tvA);
        tvB = findViewById(R.id.tvB);
        tvC = findViewById(R.id.tvC);
        tvD = findViewById(R.id.tvD);

        // 클릭 이벤트
        //1. event를 감지하고 실제 일처리하는 클래스 지정
        //tvA.setOnClickListener(onClickListener_를 가지고 있는 객체 );   // listener 지정
        tvA.setOnClickListener(this);
        //2. 이벤트 2 - 익명 내부 클래스
        tvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvC.setText("KOREA SEOUL");
            }
        });

        // 3. 이벤트 3
        tvC.setOnClickListener(new innerClass());
    }
    // alt + insert = 단축키
    // 실제 일처리하는 메소드
    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "글자를 눌렀습니다.", Toast.LENGTH_SHORT).show();
        tvB.setVisibility(View.VISIBLE);
        tvB.setText("대한민국");
    }

    // 3. 이벤트 3 : 내브 클래스가 리스너 클래스를 가지고 있다.
    class innerClass implements View.OnClickListener {
        // alt + insert = 단축키
        @Override
        public void onClick(View v) {
            tvD.setVisibility(View.VISIBLE);
            tvD.setTextColor(Color.CYAN);
        }
    }
    public void ch_tvA(View v){
        tvA.setTextColor(Color.MAGENTA);
        tvA.setTextSize(76);
    }
}
