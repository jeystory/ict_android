package com.example.ex15_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {
    //  리스트뷰도 spinner와 마찬가지로 어댑터를 사용하는 어댑트뷰
    // 다른 점은 스피너는 배열, 리스트뷰는 ArrayList를 사용
    // 스피너 이벤트 setOnItemSelectedListener, 리스트뷰 이벤트 : setOnItemClickListener
    ListView listView1;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        listView1 = findViewById(R.id.listView1);

        //내용추가
        list.add("나라선택");
        list.add("한국");
        list.add("중국");
        list.add("영국");
        list.add("태국");

        //adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        //리스트에 어댑트 담기
        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = (String)listView1.getAdapter().getItem(position);

                if(position>0){
                    Toast.makeText(MainActivity1.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
