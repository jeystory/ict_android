package com.example.ex10_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity3 extends AppCompatActivity {
    WebView webView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        webView3 = findViewById(R.id.webView3);

        // 사용자가 직접 html 을 만들어서 넣을 수 있다.
        String chtml = "<h1> 태그연습 </h1>" +
                "<h2> 안드로이드 webView 연습</h2>" +
                "<h3><ul>" +
                "<li> 레이아웃</li>"+
                "<li> 뷰와 이벤트</li>" +
                "<li> 어뎁트 뷰</li>" +
                "<li> 대화상자 메뉴/li>" +
                "</h3>";

        webView3.loadData(chtml,"text/html", "UTF-8");
    }
}
