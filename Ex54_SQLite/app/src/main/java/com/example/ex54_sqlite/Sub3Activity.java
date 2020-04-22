package com.example.ex54_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Sub3Activity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button button10 ;
    TextView count;
    ListView list;
    DAO dao;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        radioGroup = findViewById(R.id.radioGroup);
        button10 = findViewById(R.id.button10);
        count = findViewById(R.id.count);
        list = findViewById(R.id.list);

        // 라디오버튼 초기값
        radioGroup.check(R.id.desc);

        // DB 처리
        dao = DAO.db_open(this);
        cursor = dao.selectDesc();
        count.setText("전체건수 : " + cursor.getCount());

        // 리스트에 결과 표시 메소드
        disp();

        //  오름, 내림 변경
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.asc){
                    cursor = dao.selectAsc();
                    disp();
                }else if(checkedId==R.id.desc){
                    cursor = dao.selectDesc();
                    disp();
                }
            }
        });
    }
    private void disp(){
        // 리스트 뷰에 정보를 담기 위해서 배열로 만든다.
        String[] arr = new String[cursor.getCount()];
        int cnt = 0 ;

        while (cursor.moveToNext()){
            String nalja = cursor.getString(1);
            String plann = cursor.getString(2);
            arr[cnt++] = nalja + " ☞ " + plann.substring(0,4)+"...";
        }

        // 리스트 어댑터 생성
        ListAdapter adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arr);

        // 리스트 어댑터 장착
        list.setAdapter(adapter);

        // 리스트 이벤트 => 일정 보기로 넘어가자
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] nalja = parent.getAdapter().getItem(position).toString().split("☞");
                Intent intent = new Intent(Sub3Activity.this, Sub2Activity.class);
                intent.putExtra("nalja", nalja[0].trim());
                startActivity(intent);
                finish();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}