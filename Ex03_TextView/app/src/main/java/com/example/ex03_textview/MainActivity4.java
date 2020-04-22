package com.example.ex03_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// 메뉴를 이용해서 Activity와 xml을 동시에 생성하고 manifest에 등록까지
public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_main);
    }
}
