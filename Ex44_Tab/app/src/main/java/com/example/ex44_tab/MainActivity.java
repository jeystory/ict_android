package com.example.ex44_tab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //액션바
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

        //탭바
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("국내(K-POP)"));
        tabs.addTab(tabs.newTab().setText("국외(POP)"));
        tabs.addTab(tabs.newTab().setText("가수별"));
     /*  tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_menu_call));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_map));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_input_get));*/

        //이벤트 처리
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                switch(position){
                    case 0: getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();break;
                    case 1: getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();break;
                    case 2: getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
