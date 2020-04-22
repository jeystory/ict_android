package com.example.ex26_menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //팝업메뉴
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                // 이벤트 처리
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.apple:Toast.makeText(MainActivity.this, "사과 선택", Toast.LENGTH_SHORT).show(); break;
                            case R.id.banana:Toast.makeText(MainActivity.this, "바나나 선택", Toast.LENGTH_SHORT).show(); break;
                            case R.id.grape:Toast.makeText(MainActivity.this, "포도 선택", Toast.LENGTH_SHORT).show(); break;
                            case R.id.mango:Toast.makeText(MainActivity.this, "망고 선택", Toast.LENGTH_SHORT).show(); break;
                            case R.id.durian:Toast.makeText(MainActivity.this, "두리안 선택", Toast.LENGTH_SHORT).show(); break;
                        }
                        return false;
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 종료 다이얼로그
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("종료메세지")
                        .setMessage("정말 종료 할까요?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("아니도", null)
                        .show();
            }
        });
    }
}
