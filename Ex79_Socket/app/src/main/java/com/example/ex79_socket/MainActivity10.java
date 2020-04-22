package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity10 extends AppCompatActivity {
    Button json_btn;
    TextView result5;
    Handler handler = new Handler();
    String json_msg, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        json_btn = findViewById(R.id.json_btn);
        result5 = findViewById(R.id.result5);

        json_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        serverConnect("http://203.236.220.65:8090/Server01/MyController04");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                jsonProcess01();
                                result5.setText(json_msg);
                                json_msg="";
                            }
                        });
                    }
                }).start();
            }
        });
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
        msg = sb.toString();
    }

    private void jsonProcess01(){
        try{
            BufferedReader br = new BufferedReader(new StringReader(msg));
            String msg = br.readLine();
            StringBuffer sb = new StringBuffer("json parsing\n");

            JSONArray arr = new JSONArray(msg);

            for(int i=0;i<arr.length();i++){
                sb.append(arr.getJSONObject(i).getString("idx") + ", ");
                sb.append(arr.getJSONObject(i).getString("id") + ", ");
                sb.append(arr.getJSONObject(i).getString("pw") + ", ");
                sb.append(arr.getJSONObject(i).getString("name") + ", ");
                sb.append(arr.getJSONObject(i).getString("age") + ", ");
                sb.append(arr.getJSONObject(i).getString("addr") + "\n");
            }
            json_msg = sb.toString();
        }catch (Exception e){

        }
    }
}
