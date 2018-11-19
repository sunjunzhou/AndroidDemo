package e.example.playserver;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

public class PlayerService extends Service {
    private MediaPlayer mPlayer;
    IBinder iBinder=new IRemoteService.Stub() {
        public void stop() throws RemoteException {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
                try {
                    mPlayer.prepare();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mPlayer.seekTo(0);
            }

        }
        public void play() throws RemoteException{
            if (!mPlayer.isPlaying())
                mPlayer.start();
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        if (mPlayer==null){
            mPlayer=MediaPlayer.create(getApplicationContext(),R.raw.tick);
            mPlayer.setLooping(true);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),intent.getAction(),Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mPlayer!=null){
            mPlayer.stop();
            mPlayer.release();
        }
        return super.onUnbind(intent);
    }
}
