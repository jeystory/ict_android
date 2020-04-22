package com.example.ex79_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {

    EditText editText1;
    TextView result1;
    Button send_btn1;

    Socket s = null;
    Handler handler = new Handler();
    String msg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText1 = findViewById(R.id.editText1);
        send_btn1 = findViewById(R.id.send_btn1);
        result1 = findViewById(R.id.result1);

        send_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str = editText1.getText().toString();
                        msg = callServer(str);
                        //  정보를 받아서 뷰에 표시할 때 사용
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                result1.append(msg + "\n");
                                editText1.setText("");
                            }
                        });
                    }
                }).start();
            }
        });
    }

    //서버와 통신하는 메소드
    public String callServer(String str){
        String result = null;

        BufferedWriter writer = null;
        BufferedReader reader = null;

        try {
            s = new Socket("203.236.220.65", 7778);
            writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            str += "[hw]";
            writer.write(str +System.getProperty("line.separator"));
            writer.flush();

            result = reader.readLine();
            s.close();
        } catch (Exception e){

        }


        return result;

    }
}
