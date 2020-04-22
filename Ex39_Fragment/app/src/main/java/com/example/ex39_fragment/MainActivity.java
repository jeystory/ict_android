package com.example.ex39_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment_A fragment_a;
    Fragment_B fragment_b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  fragment_a = (Fragment_A)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        // fragment_b = new Fragment_B();

        // 사용 가능
        fragment_a = new Fragment_A();
        fragment_b = new Fragment_B();
    }

    // 이벤트 처리할 메소드
    public void onFragmentChange(int k){
        if(k == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_b).commit();
        else if(k == 1)
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_a).commit();
    }
}
