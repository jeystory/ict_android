package com.ict.ex87_videorecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button button1, button2, button3, button4 ;
    FrameLayout container;

    MediaPlayer player;
    MediaRecorder recorder;

    String file_name;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        container = findViewById(R.id.container);

        SurfaceView surface = new SurfaceView(this);
        holder = surface.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        container.addView(surface);

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

        // 저장
        String sdcard = Environment.getDataDirectory()+"/data/"+getPackageName();
        file_name = sdcard+ File.separator+"m_recored.mp4";

        AutoPermissions.Companion.loadAllPermissions(this,100);
    }

    private void startRecording(){
        if(recorder == null){
            recorder = new MediaRecorder();
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        recorder.setOutputFile(file_name);

        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
            recorder.start();
        }catch (Exception e){
            recorder.release();
            recorder = null;
        }
        ContentValues values = new ContentValues(10);
        values.put(MediaStore.MediaColumns.MIME_TYPE,"video/mp4");
        values.put(MediaStore.MediaColumns.TITLE,"RecordVideo");
        values.put(MediaStore.MediaColumns.DATE_ADDED,System.currentTimeMillis()/1000);

        values.put(MediaStore.Audio.Media.DATA,file_name);


        Uri videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,values);

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, videoUri));
    }
    private void stopRecording(){
        if(recorder == null){
            return;
        }
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
    }
    private void startPlay(){
        if(player == null){
            player = new MediaPlayer();
        }

        try {
            player.setDataSource(file_name);
            player.setDisplay(holder);

            player.prepare();
            player.start();

        }catch (Exception e){
        }
    }
    private void stopPlay(){
        if(player == null){
            return;
        }
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}
}
