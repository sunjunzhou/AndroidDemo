package com.example.mybroadcast2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button myButton;
    private BroadcastReceiver myReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle mybundle=intent.getExtras();
            String str1=mybundle.getString("data1");
            String str2=mybundle.getString("data2");
            Toast.makeText(context,str1+""+str2,Toast.LENGTH_LONG).show();
            tv.setText(str1+""+str2);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter myintentfilter=new IntentFilter();
        myintentfilter.addAction("com.example.broadcast.MY_BROADCAST");
        this.registerReceiver(myReceiver,myintentfilter);
        tv=(TextView)findViewById(R.id.broadcastshow);
        tv.setText("");
        myButton=(Button)findViewById(R.id.send);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent("com.example.broadcast.MY_BROADCAST");
                Bundle bundle=new Bundle();
                bundle.putString("data1","自定义广播与接收案例");
                bundle.putString("data2","\n  孙俊洲～...0v0...");
                myintent.putExtras(bundle);
                sendBroadcast(myintent);
            }
        });
    }
}
