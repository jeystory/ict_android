package com.example.ex17_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button1, button2;
    ListView listView;
    ArrayAdapter<String> adapter ;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        listView = findViewById(R.id.listView);

        list.add("fruit");
        list.add("apple");
        list.add("strawberry");

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                list.add(msg);
                editText.setText("");
                //한템포 늦게 반응
                //리스트에 데이터가 추가되었음을 시스템에게 알리고 바로 새로 고침하게 만들자
                adapter.notifyDataSetChanged();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                list.remove(msg);
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
