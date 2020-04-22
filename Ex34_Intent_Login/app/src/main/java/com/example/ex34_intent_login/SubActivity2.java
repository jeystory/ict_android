package com.example.ex34_intent_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity2 extends AppCompatActivity {
    Button button5, button6, button11;
    String username;
    String password;
    String titleMsg;
    Intent rcvMainIntent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        button5 = findViewById(R.id.button5);
        button6  = findViewById(R.id.button6);
        button11  = findViewById(R.id.button11);

        rcvMainIntent1 = getIntent();
        username = rcvMainIntent1.getStringExtra("ID");
        password = rcvMainIntent1.getStringExtra("PWD");
        titleMsg = rcvMainIntent1.getStringExtra("titleMag");

        //Toast.makeText(this, username + password + titleMsg, Toast.LENGTH_SHORT).show();
        //고객
        // 메인으로
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult(RESULT_OK);
                //finish();
                Intent go_menu = new Intent(SubActivity2.this,MainActivity.class);
                go_menu.putExtra("username", username);
                go_menu.putExtra("password", password);
                go_menu.putExtra("titleMsg", "고객화면");
                setResult(RESULT_OK, go_menu);

            }
        });

        //로그인으로
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(SubActivity2.this,MainActivity.class);
                go_home.putExtra("res", "res1");
                go_home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(go_home);
                //startActivityForResult(sndMainIntent1, 120);
                //setResult(RESULT_OK);

            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home2 = new Intent();
                setResult(RESULT_CANCELED, go_home2);
            }
        });

    }
}
