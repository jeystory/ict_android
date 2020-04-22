package com.example.ex23_alert_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6;
    final String[] foods ={"chicken", "Pork Belly", "GreenOnion cake"};
    final boolean[] isFoods = {false, false, false};
    ArrayList<String> k_foods = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog1")
                        .setMessage("Dialog Context")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Program End")
                        .setMessage("Exit Program?")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] foods ={"chicken", "Pork Belly", "GreenOnion cake"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog3")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setItems(foods, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                /*int position => 선택된 항목의 위치값*/
                                String msg = foods[position];
                                Toast.makeText(MainActivity.this, msg +" 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog 4")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setSingleChoiceItems(foods, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                String msg = foods[position];
                                Toast.makeText(MainActivity.this, msg +" 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog 5")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setMultiChoiceItems(foods, isFoods, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                                if(isChecked){
                                    Toast.makeText(MainActivity.this, foods[position]+ " 선택", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, foods[position]+ " 해제", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog 6");
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes Button Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setMultiChoiceItems(foods, isFoods, new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked == true){
                   k_foods.add(foods[which]);
                } else {
                    k_foods.remove(foods[which]);
                }
            }
        });

        // 중복해서 어러개 만들어도 나중에 만들어진 positive 나 negative 버튼 한개씩만 화면에 출력된다.
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, "Confirm Button Click", Toast.LENGTH_SHORT).show();
               String msg ="";
                for(int i=0;i<k_foods.size() ;i++){
                   msg += k_foods.get(i);
                    msg += ", ";
               }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "No Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0;i<isFoods.length;i++)   isFoods[i] = false;
                Toast.makeText(MainActivity.this, "Cancel Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}
