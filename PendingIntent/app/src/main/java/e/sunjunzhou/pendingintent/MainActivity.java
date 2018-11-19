package e.sunjunzhou.pendingintent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    Notification notification;
    private static final int NOTIFICATION_ID=1;
    private Button notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyBtn=(Button)this.findViewById(R.id.button1);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                String ns= Context.NOTIFICATION_SERVICE;
                nm=(NotificationManager)getSystemService(ns);
                int icon=R.drawable.ic_launcher_background;
                long when=System.currentTimeMillis();
                Notification.Builder builder=new Notification.Builder(MainActivity.this);
                builder.setSmallIcon(icon)
                        .setWhen(when)
                        .setDefaults(Notification.DEFAULT_SOUND);
                notification=builder.build();
                Intent intent=new Intent(MainActivity.this,OtherActivity.class);
                PendingIntent p_intent=PendingIntent.getActivities(MainActivity.this,0,intent,0);
                String temp="单机我后将打开一个新的Activity";
                notification.setLatestEventInfo(MainActivity.this,"我是通知的标题",temp,p_intent);
                nm.notify(NOTIFICATION_ID,notification);
            }
        });
    }
}
