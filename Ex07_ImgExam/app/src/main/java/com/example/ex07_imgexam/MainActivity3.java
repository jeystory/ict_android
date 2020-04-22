package com.example.ex07_imgexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText editText1, editText2;
    Button btn4, btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        editText1.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                byte[] b = s.toString().getBytes();
                int sCnt = b.length;
                editText2.setText(sCnt + "/80byte");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText1.getText().toString();
                Toast.makeText(MainActivity3.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
