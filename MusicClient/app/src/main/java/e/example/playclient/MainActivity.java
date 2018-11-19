package e.example.playclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION="e.example.playerserver.PlayerService";
    private boolean isBinded=false;
    private IRemoteService mService;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            mService=IRemoteService.Stub.asInterface(arg1);
            isBinded=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBinded=false;
            mService=null;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(ACTION);
        bindService(intent,conn, Context.BIND_ABOVE_CLIENT);
        Button bt1=(Button)findViewById(R.id.play);
        Button bt2=(Button)findViewById(R.id.stop);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mService.play();
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mService.stop();
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (isBinded){
            unbindService(conn);
            mService=null;
            isBinded=false;
        }
        super.onDestroy();
    }
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
