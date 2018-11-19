package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

public class MyAudioService extends Service {
    private MediaPlayer mediaplayer;
    public class PlayBinder extends Binder{
    public void MyMethod() {
        Toast.makeText(getApplicationContext(), "我是服务里的方法", Toast.LENGTH_LONG).show();
        String path = "sdcard/Music/white.mp3";
         mediaplayer = new MediaPlayer();
        try {
            mediaplayer.setDataSource(path);
            mediaplayer.prepare();
            mediaplayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    @Override
    public void onDestroy() {
        if (mediaplayer!=null){
            mediaplayer.stop();
            mediaplayer.release();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {

        return new PlayBinder();
    }
}
