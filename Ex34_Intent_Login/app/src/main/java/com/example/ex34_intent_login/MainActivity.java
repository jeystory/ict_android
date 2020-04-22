package com.example.ex34_intent_login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText editText1, editText2;
    Intent rcvLogIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);

        String rcvmsg= "";
        rcvLogIntent = getIntent();
        String res = rcvLogIntent.getStringExtra("res");

     /*   if(res.equals("")) rcvmsg="";
        else if(res.equals("res1"))  rcvmsg = "고객관리";
        else if(res.equals("res2")) rcvmsg = "매출관리" ;
        else if(res.equals("res3")) rcvmsg = "상품관리";*/

        Toast.makeText(this, rcvmsg, Toast.LENGTH_SHORT).show();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logId=editText1.getText().toString();
                String  logPwd =editText2.getText().toString();

                if(logId.equals("") || (logPwd.equals(""))){
                    Toast.makeText(MainActivity.this, "아이디와 패스워드를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                   Intent mainIntent = new Intent(MainActivity.this, SubActivity1.class);
                    mainIntent.putExtra("logId", logId);
                    mainIntent.putExtra("logPwd",logPwd );
                    startActivityForResult(mainIntent, 100);
                    //finish();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String msg = "";

        if(requestCode == 100){
            if(resultCode == RESULT_OK) {
                String menu = data.getStringExtra("menu");
                String message = data.getStringExtra("message");
                Toast.makeText(this, menu + "," + message, Toast.LENGTH_SHORT).show();
                editText1.setText("");
                editText2.setText("");
            }
        }else if(requestCode == 120){
            msg = "From : 고객관리 화면 To: 로그인화면 Msg : ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
           /* if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/
        } else if(requestCode == 130){
            msg = "From : 매출관리 화면 To: 로그인화면 Msg : ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            /*if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/
        }else if(requestCode == 140){
            msg = "From : 상품관리 화면 To: 로그인화면 Msg : ";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            /*if(resultCode==RESULT_OK) {
                msg += "RESULT_OK";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            } else if(resultCode==RESULT_CANCELED){
                msg += "RESULT_CANCELED";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }*/
        }else {

        }
    }

}
