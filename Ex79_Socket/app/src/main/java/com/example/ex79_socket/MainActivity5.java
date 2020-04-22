package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity5 extends AppCompatActivity {
    Button down;
    TextView result2;
    String html;
    Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        down = findViewById(R.id.down);
        result2 = findViewById(R.id.result2);

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 네트워크는 반드시 스레드에서 처리
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //html = downloadHTML("http://google.com");
                        html = downloadHTML( "http://203.236.220.65:8090/Server01/index.jsp");

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                result2.setText(html);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public String downloadHTML(String addr){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 연결 설공했으면
            if(conn != null){
                // 타임아웃 설정
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(
                                    conn.getInputStream(), "utf-8" ));
                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();;
            }
        }catch (Exception e){

        }
        return sb.toString();
    }
}
