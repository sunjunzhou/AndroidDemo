package e.sunjunzhou.audio_service1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class MyAudioService extends Service {
    private MediaPlayer mediaplayer;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
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
    public IBinder onBind(Intent intent) {
        return null;
    }
}
