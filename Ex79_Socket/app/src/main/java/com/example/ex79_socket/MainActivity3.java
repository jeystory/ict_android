package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity3 extends AppCompatActivity {
    static final String IP = "203.236.220.65";
    static final int PORT = 7880;

    Socket socket;
    Handler handler = new Handler();

    BufferedWriter writer;
    BufferedReader reader;

    EditText edtMsg;
    Button btnSend;
    TextView txtResult ;
    String txtReceiver ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edtMsg = findViewById(R.id.edtMsg);
        btnSend = findViewById(R.id.btnSend);
        txtResult = findViewById(R.id.txtResult);

        new Thread(new Runnable() {
            @Override
            public void run() {
                connet();
            }
        }).start();

        // 보내기
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendMessage(IP + ":" + edtMsg.getText().toString());
                        }
                    }).start();
                    edtMsg.setText("");
                }catch (Exception e) {
                    Log.d("my", e+"") ;
                }
            }
        });
    }

    private void connet(){
        try {
            socket = new Socket(IP,PORT);
            if(socket != null){
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 접속이 끊어지면 false
                while(socket.isConnected()){
                    try {
                        txtReceiver = reader.readLine();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                txtResult.setText(txtResult.getText().toString()+"\n"+txtReceiver);
                            }
                        });
                    }catch (Exception e){
                        Log.d("my","1");
                    }
                }
            }
        }catch (Exception e){
        }
    }

    public void sendMessage(String msg){
        try {
            writer.write(msg + System.getProperty("line.separator"));
            writer.flush();
        }catch (Exception e){
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket.close();
        }catch (Exception e){  }
    }
}
