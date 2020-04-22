package com.example.ex43_actionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        View view = menu.findItem(R.id.menu_search).getActionView();
        if(view != null){
            editText = view.findViewById(R.id.editText);
            if(editText != null){
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_WEB_SEARCH);

                        //검색할 내용
                        intent.putExtra(SearchManager.QUERY, editText.getText().toString());
                        if(intent.resolveActivity(getPackageManager() )!= null){
                            startActivity(intent);
                        }
                        editText.setText("");
                        return true;
                    }
                });
            }
        }
        return true;
    }
}
