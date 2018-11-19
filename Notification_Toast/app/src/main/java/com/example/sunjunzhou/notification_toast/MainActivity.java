package com.example.sunjunzhou.notification_toast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    Notification notification;
    private static final int NOTIFICATION_ID=1;
    private Button notifyBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        notifyBtn=this.findViewById(R.id.button1);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                String ns= Context.NOTIFICATION_SERVICE;
                nm=(NotificationManager)getSystemService(ns);
                int icon=R.drawable.ic_launcher_background;
                long when=System.currentTimeMillis();
                Notification.Builder builder=new Notification.Builder(MainActivity.this);
                builder.setContentTitle("我是通知的标题")
                        .setContentText("我是通知的内容")
                        .setSmallIcon(icon)
                        .setWhen(when)
                        .setDefaults(Notification.DEFAULT_SOUND);
                notification=builder.build();
                nm.notify(NOTIFICATION_ID,notification);

            }
        });
        cancelBtn=this.findViewById(R.id.button2);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm.cancel(NOTIFICATION_ID);
                Toast.makeText(MainActivity.this,"已经取消了指定的通知，可查验！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
