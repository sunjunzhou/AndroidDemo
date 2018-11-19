package e.sunjunzhou.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class MyAudioService extends Service{
    private MediaPlayer mediaplayer;

    public void onCreate() {
        super.onCreate();
    }


    public void onStart(Intent intent, int startId) {
        String path="sdcard/music/white.mp3";
        mediaplayer=new MediaPlayer();
        try{
            mediaplayer.setDataSource(path);
            mediaplayer.prepare();
            mediaplayer.start();

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    public void onDestroy() {
        if (mediaplayer!=null){
            mediaplayer.stop();
            mediaplayer.release();
        }
        super.onDestroy();
    }

    @Nullable

    public IBinder onBind(Intent intent) {
        return null;
    }
}
