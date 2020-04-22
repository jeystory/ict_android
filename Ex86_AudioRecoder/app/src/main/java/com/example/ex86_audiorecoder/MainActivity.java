package com.example.ex86_audiorecoder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    MediaRecorder recorder;
    MediaPlayer player;
    String file_name;
    Button button1, button2, button3, button4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay();
            }
        });

        // String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = Environment.getDataDirectory()+"/data/"+getPackageName();
        file_name = path+ File.separator+"r_mag.mp4";

        AutoPermissions.Companion.loadAllPermissions(this, 100);

    }

    public void startRecording(){
        if(recorder  == null){
            recorder = new MediaRecorder();
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(file_name);

        try {
            recorder.prepare();
            recorder.start();
        }catch (Exception e){
        }
    }
    public void stopRecording(){
        if(recorder == null){
            return;
        }
        recorder.stop();
        recorder.release();
    }

    public void startPlay(){
        killMediaPlayer();

        try{
            player = new MediaPlayer();
            player.setDataSource("file://"+file_name);
            player.prepare();
            player.start();
        } catch(Exception e){}
    }
    public void stopPlay(){
        if(player == null){
            return;
        }
        player.stop();
        player.release();
    }

    private void killMediaPlayer(){
        if(player != null){
            try {
                player.release();
            }catch (Exception e){
            }
        }
    }
    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
