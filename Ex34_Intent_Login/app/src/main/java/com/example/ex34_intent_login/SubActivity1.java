package com.example.ex34_intent_login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity1 extends AppCompatActivity {
    Button button2,button3, button4, button10;
    String rcvId, rcvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button10 = findViewById(R.id.button10);


        Intent rcvLogIntent = getIntent();

        rcvId = rcvLogIntent.getStringExtra("logId");
        rcvPwd = rcvLogIntent.getStringExtra("logPwd");

        // 고객
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subIntent1 = new Intent(SubActivity1.this, SubActivity2.class);
                subIntent1.putExtra("ID", rcvId);
                subIntent1.putExtra("PWD", rcvPwd);
                subIntent1.putExtra("titleMsg", "메인->고객관리");

                startActivityForResult(subIntent1, 1000);
                //finish();
            }
        });

        // 매출
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMerchIntent = new Intent(SubActivity1.this, SubActivity3.class);
                startActivityForResult(mainMerchIntent, 130);
               // finish();
            }
        });

        //상품
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainSellIntent = new Intent(SubActivity1.this, SubActivity4.class);
               /* mainSellIntent.putExtra("logId", rcvId);
                mainSellIntent.putExtra("logPwd", rcvPwd);*/
                startActivityForResult(mainSellIntent, 140);
               // finish();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sndIntent = new Intent();
                sndIntent.putExtra("menu", "메뉴화면");
                sndIntent.putExtra("message", "성공");
                setResult(RESULT_OK, sndIntent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String msg = "";

        if(requestCode == 1000){
            if(resultCode==RESULT_OK) {
                String name = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                String titleMsg = data.getStringExtra("titleMsg");
            } else if(requestCode == RESULT_CANCELED){
                Intent goLogPg = new Intent();
            }
            msg = "From : 고객관리 화면 To: 메인화면 Msg  ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
           /* if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/

        } else if(requestCode == 2000){
            msg = "From : 매출관리 화면 To: 메인화면 Msg  ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            /*if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/
        }else if(requestCode == 3000){
            msg = "From : 상품관리 화면 To: 메인화면 Msg ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
           /* if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/
        }else {
            Toast.makeText(this, "what?????", Toast.LENGTH_SHORT).show();
        }
    }
}
