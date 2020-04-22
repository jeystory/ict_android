package com.example.ex55_viewpager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    TextView btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        // 뷰페이져의 내용은 어댑터를 이용한다.
        MyPagerAdater adater = new MyPagerAdater(getSupportFragmentManager());

        fragment1 = new Fragment1();
        adater.addItem(fragment1);

        fragment2 = new Fragment2();
        adater.addItem(fragment2);

        fragment3 = new Fragment3();
        adater.addItem(fragment3);

        // 뷰페이져의 내용은 어댑터를 이용한다.
        pager.setAdapter(adater);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });
    }

    // 어댑터안에 프레그먼트을 넣어서 활용
    class MyPagerAdater extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<>();

        public MyPagerAdater(FragmentManager fm) {  super(fm);        }
        // 플레그먼트 추가
        public  void addItem(Fragment item){    items.add(item);        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지"+(position+1);
        }
        @Override
        public Fragment getItem(int position) {  return items.get(position);        }
        @Override
        public int getCount() {  return items.size();   }
    }
}
