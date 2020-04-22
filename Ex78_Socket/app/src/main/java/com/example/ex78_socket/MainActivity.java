package com.example.ex78_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText1;

    TextView textView1, textView2;
    Button button1, button2 ;

    // 안드로이드에서 네트워크는 반드시 스레드 처리 해야 된다.
    // 핸들러 사용 해야 된다.
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        textView2 = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = editText1.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }


    public void send(String data){
        try {
            int port = 5001;
            Socket socket = new Socket("localhost", port);
            printClientLog("소곗 연결함");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터 전송함.");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로 부터 받음 : " + inputStream.readObject());
            socket.close();
        }catch (Exception e){
        }

    }
    public void  printClientLog(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView1.append(data+"\n");
            }
        });
    }

    public void startServer(){
        try {
            int port = 5001;
            ServerSocket ss = new ServerSocket(port);
            printServerLog("서버시작함 : " + port);

            while (true){
                Socket s = ss.accept();
                InetAddress clientHost = s.getLocalAddress();
                int clientPort = s.getPort();
                printServerLog("클라이언트 연결됨 : " + clientHost +":"+clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터 받음 : " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
                outputStream.writeObject(obj + "from server");
                outputStream.flush();
                printServerLog("데이터 보냄");

                ss.close();
            }
        }catch (Exception e){
        }
    }

    public void  printServerLog(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data+"\n");
            }
        });
    }
}


