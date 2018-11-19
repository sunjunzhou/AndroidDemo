package e.sunjunzhou.mybroadcaststatic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String str1=bundle.getString("data1");
        String str2=bundle.getString("data2");
        int year=bundle.getInt("year");
        Toast.makeText(context,str1+"\n"+str2+","+year,Toast.LENGTH_LONG).show();

    }
}
