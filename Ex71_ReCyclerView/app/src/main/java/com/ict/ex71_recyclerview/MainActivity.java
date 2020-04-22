package com.ict.ex71_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2, editText3 ;
    Button button1;
    TextView count;

    RecyclerView recyclerView;
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        button1 = findViewById(R.id.button1);
        count = findViewById(R.id.count);
        count.setText("3 명");

        recyclerView = findViewById(R.id.recyclerView);

       //  LinearLayoutManager manager
       //          = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        GridLayoutManager manager =
                new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        adapter = new PersonAdapter();

        adapter.addItem(new VO("홍길동", "1988-08-08", "010-8888-8888",R.mipmap.ic_launcher_round));
        adapter.addItem(new VO("마이콜", "1998-03-01", "010-0303-1010",R.drawable.gingerbread));
        adapter.addItem(new VO("희동이", "2008-10-01", "010-1010-0101"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClicListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                VO vo = adapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ vo.getPhone()));
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String birth = editText2.getText().toString();
                String phone = editText3.getText().toString();

                adapter.addItem(new VO(name,birth,phone,R.drawable.gingerbread));
                adapter.notifyDataSetChanged();
                count.setText(adapter.getItemCount()+" 명");
            }
        });
    }
}
