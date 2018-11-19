package e.sunjunzhou.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import e.sunjunzhou.btos.MainActivity;
import e.sunjunzhou.service.MyAudioService;

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, MyAudioService.class);
        context.startService(intent1);
        Intent myintent=new Intent(context,MainActivity.class);
        myintent.putExtra("iscast",true);
        context.startActivity(myintent);

    }
}
