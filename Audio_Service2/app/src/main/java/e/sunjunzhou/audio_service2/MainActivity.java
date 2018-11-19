package e.sunjunzhou.audio_service2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.services.MyAudioService;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyAudioService.PlayBinder myAudioBinder=(MyAudioService.PlayBinder)service;
            myAudioBinder.MyMethod();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(this,MyAudioService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new MyListener());

    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            unbindService(conn);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK==keyCode)
            return false;
        return MainActivity.this.onKeyDown(keyCode,event);
    }
}
