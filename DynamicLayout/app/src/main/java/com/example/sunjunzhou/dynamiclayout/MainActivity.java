package com.example.sunjunzhou.dynamiclayout;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=(Button)findViewById(R.id.click1);
        btn1.setOnClickListener(listener);
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater=MainActivity.this.getLayoutInflater();
            View layout=inflater.inflate(R.layout.login_view,null);
            //builder.setIcon(R.drawable.tb02);
            builder.setTitle("用户登录");
            builder.setView(layout);
            builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });
            builder.create().show();
        }
    };
    }

