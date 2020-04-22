package com.example.ex62_myreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editText = findViewById(R.id.editText);
       textView = findViewById(R.id.textView);
       button = findViewById(R.id.button);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String input =  editText.getText().toString();
               Intent s_intent = new Intent(getApplicationContext(),MyService.class);
               s_intent.putExtra("msg", input);

               startService(s_intent);
           }
       });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("my", "onNewIntent()");
        if(intent != null){
            textView.setText("받은 결과 : "+ intent.getStringExtra("msg"));
        }
    }
}
