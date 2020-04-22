package com.ict.ex70_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

         LinearLayoutManager manager
                 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

       // GridLayoutManager manager =
       //         new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        adapter  = new PersonAdapter();

        adapter.addItem(new VO("김길동", "010-7777-7777"));
        adapter.addItem(new VO("홍길동", "010-8888-8888"));
        adapter.addItem(new VO("고길동", "010-9999-9999"));
        adapter.addItem(new VO("이길동", "010-7777-7777"));
        adapter.addItem(new VO("박길동", "010-8888-8888"));
        adapter.addItem(new VO("노길동", "010-9999-9999"));
        adapter.addItem(new VO("강길동", "010-7777-7777"));
        adapter.addItem(new VO("조길동", "010-8888-8888"));
        adapter.addItem(new VO("차길동", "010-9999-9999"));


        recyclerView.setAdapter(adapter);

        // 리스너 이벤트
        adapter.setOnItemClickListener(new OnPersonitemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                VO vo = adapter.getItem(position);
                // Toast.makeText(getApplicationContext(), "아이템 선택 : " + vo.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+vo.getMobile()));
                startActivity(intent);
            }
        });

    }
}
