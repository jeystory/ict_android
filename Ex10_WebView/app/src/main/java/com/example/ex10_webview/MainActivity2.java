package com.example.ex10_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText editText1;
    Button button1;
    WebView webView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText1 = findViewById(R.id.editText1);
        button1 = findViewById(R.id.button1);
        webView2 = findViewById(R.id.webView2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText1.getText().toString();
                webView2.setWebViewClient(new WebViewClient());
                webView2.loadUrl(url);
            }
        });

    }
}
