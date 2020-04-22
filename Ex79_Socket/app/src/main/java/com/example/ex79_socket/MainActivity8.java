package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity8 extends AppCompatActivity {
    Handler handler = new Handler();
    String text_msg;
    WebView result3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        result3 = findViewById(R.id.result3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                serverConnect("http://203.236.220.65:8090/Server01/MyController01");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      process01();
                    }
                });
            }
        }).start();


    }

    public void serverConnect(String str){
        StringBuffer sb = new StringBuffer();
        try{
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null) {
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch (Exception e){
            System.out.println(e);
        }
        //return sb.toString();
        text_msg = sb.toString();
    }

    // 텍스트 메세지를 가공 처리 하기
    public void process01(){
        StringBuffer sb = new StringBuffer();
        sb.append("<style type=\"text/css\">" +
                "table { width: 100%; color : blue}" +
                "table, th, td {border: 1px solid red; text-align: center;}" +
                "</style>");
        sb.append("<h2>텍스트 출력</h2>");
        sb.append("<h2>print text</h2>");
        sb.append("<table width='100%' border='1'><thead><tr><th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th><th>주소</th></tr></thead>");
        String[] msg = text_msg.split("/") ;
        sb.append("<tbody");
        for (String k:msg) {
            sb.append("<tr>");
            String[] cols = k.split(",");
            for (String k2:cols) {
                sb.append("<td>" + k2 + "</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</tbody> </table>");
        result3.loadData(sb.toString(),"text/html","utf-8");


    }
}
