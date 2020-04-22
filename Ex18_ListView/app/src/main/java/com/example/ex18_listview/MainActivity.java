package com.example.ex18_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<VO> list = new ArrayList<>();
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        list.add(new VO(R.drawable.pic1, "아기사진1"));
        list.add(new VO(R.drawable.pic2, "아기사진2"));
        list.add(new VO(R.drawable.pic3, "아기사진3"));
        list.add(new VO(R.drawable.pic4, "아기사진4"));
        list.add(new VO(R.drawable.pic5 ,"아기사진5"));
        list.add(new VO(R.drawable.pic6 ,"아기사진6"));

        adapter = new UserAdapter(this, R.layout.user_item, list);
        listView.setAdapter(adapter);


    }
}
