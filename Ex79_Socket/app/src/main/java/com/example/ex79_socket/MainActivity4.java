package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity4 extends AppCompatActivity {

    Button btn1, btn2;
    ListView list;

    Socket s;
    Handler handler = new Handler();
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        list = findViewById(R.id.list);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = callServer("203.236.220.65", 7888, "test");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity4.this, result, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = callServer("203.236.220.65", 7888, "db");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               //listView에 배열로 저장
                                String[] data = null;
                                try{
                                    data = result.split(",");
                                    ArrayAdapter<String> adapter =
                                            new ArrayAdapter<>(MainActivity4.this, android.R.layout.simple_list_item_1, data);
                                    list.setAdapter(adapter);

                                } catch (Exception e){

                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public String callServer(String ip, int port, String msg){
       String str = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;

       try {
           s = new Socket(ip, port);
           writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
           msg = msg+System.getProperty("line.separator");
           writer.write(msg);
           writer.flush();

           str = reader.readLine();

       } catch (Exception e){

       } finally {
           try {
            s.close();
           } catch (Exception e){}
       }
       return str;
    }
}
