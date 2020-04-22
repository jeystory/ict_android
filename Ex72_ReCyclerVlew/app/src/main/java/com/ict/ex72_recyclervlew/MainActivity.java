package com.ict.ex72_recyclervlew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CustomerAdapter();

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        adapter.addItem(new VO("코트 1", "547700", "이달의 상품", R.drawable.clothes1));
        adapter.addItem(new VO("코트 2", "647700", "베스트 상품", R.drawable.clothes2));
        adapter.addItem(new VO("코트 3", "597700", "이달의 상품", R.drawable.clothes3));
        adapter.addItem(new VO("코트 4", "517700", "이벤트 상품", R.drawable.clothes4));
        adapter.addItem(new VO("코트 5", "547700", "이달의 상품", R.drawable.clothes1));
        adapter.addItem(new VO("코트 6", "647700", "베스트 상품", R.drawable.clothes2));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListner(new OnCustomerListener() {
            @Override
            public void onItemClick(CustomerAdapter.ViewHolder holder, View view, int position) {
                VO vo = adapter.getItem(position);
                Toast.makeText(MainActivity.this, vo.getName()+"  선택됨", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

