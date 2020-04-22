package com.example.ex24_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Option Menu : 주메뉴, 메뉴버튼을 클릭하면 나오는 메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Create Option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem Item = menu.add(0,0,0,"짜장면");
        menu.add(0,1,0,"짬뽕");
        menu.add(0,2,0,"탕수육");
        //SubMenu sm = menu.addSubMenu(0,3,0,"만두");
        SubMenu sm = menu.addSubMenu("만두");
        sm.add(0,4,0,"물만두");
        sm.add(0,5,0,"군만두");
        return super.onCreateOptionsMenu(menu);
    }

    // Event handler Option Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*MenuItem item : 선택된 메뉴 전달*/
        Toast.makeText(this, item.getTitle() + "selected", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
