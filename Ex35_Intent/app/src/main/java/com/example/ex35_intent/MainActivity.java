package com.example.ex35_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
// 암시적 인텐트는 약속된 액션을 지정하여 안드로이드에서 제공하는 기존 응용 프로그램(기능)을 실행
public class MainActivity extends AppCompatActivity {

    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL); // 액션
                intent.setData(Uri.parse("tel:01097329110")); // setData

              //  Intent intent2 = new Intent(Intent.ACTION_DIAL);
              //  intent.setData(Uri.parse("tel:01097329110"));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
              */
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL); // 액션
                intent.setData(Uri.parse("tel:01073519251")); // setData
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // 23 이상에서 가능 , 앱 정보에서 전화걸기 권한 허용도 해야 된다.  => 실제로 전화 걸림
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    startActivity(intent);
                }
            }
        });

        // 인터넷 사이트 : 허가 받지 않음
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("https://m.naver.com"));
                intent.setData(Uri.parse("https://www.naver.com"));
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // 구글맵
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.com/maps?q="+37.557527 +","+126.924466+", z=14"));
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // 검색
        // 구글맵
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                // 검색 내용
                intent.putExtra(SearchManager.QUERY,"홍대입구역");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //문자보내기
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                // 검색 내용
                intent.setData(Uri.parse("sms:010-7351-9251"));
                intent.putExtra("sms_body", "지금은 전화를 받을 수 없습니다.");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //갤러리 호출
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setType("image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //camera
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        //주소록
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(ContactsContract.Contacts.CONTENT_URI);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
