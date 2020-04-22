package com.example.ex25_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    // 이벤트

    // xml에서 메뉴를 만들어서 참조 한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 이벤트

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Toast.makeText(this, item.getTitle()+" 선택", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case R.id.apple: Toast.makeText(this, "사과선택", Toast.LENGTH_SHORT).show(); break;
            case R.id.banana: Toast.makeText(this, "바나나선택", Toast.LENGTH_SHORT).show(); break;
            case R.id.grape: Toast.makeText(this, "포도선택", Toast.LENGTH_SHORT).show(); break;
            case R.id.mango: Toast.makeText(this, "망고선택", Toast.LENGTH_SHORT).show(); break;
            case R.id.durian: Toast.makeText(this, "두리안선택", Toast.LENGTH_SHORT).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }

}
