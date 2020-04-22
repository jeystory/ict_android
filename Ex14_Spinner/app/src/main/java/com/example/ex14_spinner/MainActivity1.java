package com.example.ex14_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    // spinner => dropdown 목록과 같음
    // 1. 반드시 어뎁터를 사용 => 어뎁터뷰
    // 2. 어뎁터에 들어갈 내용을 미리 생성(배열)
    Spinner spinner1,spinner2, spinner3;
    ImageView imageView1;
    int[] imgId = {R.drawable.boy, R.drawable.coffe, R.drawable.dog, R.drawable.donald};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        spinner1 = findViewById(R.id.spinner1);
        //1. 스피너에 들어갈 데이터를 배열로 만든다.
        String[] arr1 = {"과일종류", "망고", "두리안", "용과", "람부탄", "메론"};
        //2. 어텝터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,   //컨텍스트 선택
                android.R.layout.simple_spinner_dropdown_item,  // 모양
                arr1);  //들어갈 내용
         // 3.  스피너에 어댑터 장착
        spinner1.setAdapter(adapter);

        //4.  스피너 이벤트 처리
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //선택된 배열의 위치 : position, 선택된 객체의 아이 : id
                String msg = (String)spinner1.getAdapter().getItem(position);
                if(position > 0){
                    Toast.makeText(MainActivity1.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // spinner2 -> 미리 작업한다.
        // values에서 arrays 미리 작업 - xml의 등록 - values
        //이벤트 처리는 해줘야 함
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = (String)spinner2.getAdapter().getItem(position);
                if(position > 0){
                    Toast.makeText(MainActivity1.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // spinner3
        spinner3 = findViewById(R.id.spinner3);
        imageView1 = findViewById(R.id.imageView1);
        String[] arr3 = {"그림종류", "소년", "커피", "개", "도날드"};
        //2. 어텝터 생성
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this,   //컨텍스트 선택
                android.R.layout.simple_spinner_dropdown_item,  // 모양
                arr3);  //들어갈 내용
        // 3.  스피너에 어댑터 장착
        spinner3.setAdapter(adapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position >0  && position <5){
                    imageView1.setImageResource(imgId[position-1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}
