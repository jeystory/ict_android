package com.example.ex10_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
// 인터넷 사용 설정 : manifest에서 설정
public class MainActivity1 extends AppCompatActivity {
    WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        webView1 = findViewById(R.id.webView1);

        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("http://naver.com");
    }


}
