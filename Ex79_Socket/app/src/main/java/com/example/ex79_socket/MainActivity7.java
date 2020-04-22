package com.example.ex79_socket;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity7 extends AppCompatActivity {
    WebView webView2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        webView2 = findViewById(R.id.webView2);

        // 사용자가 직접 html 생성해서 넣을 수 있음
        String customerHTML="<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Insert title here</title>" +
                "<style type=\"text/css\">" +
                "\th2{" +
                "\t\tcolor : blue;" +
                "\t}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "\t<h2>Welcome to KOREA</h2>" +
                "</body>" +
                "</html>";

        webView2.loadData(customerHTML, "text/html", "UTF-8");

    }
}
