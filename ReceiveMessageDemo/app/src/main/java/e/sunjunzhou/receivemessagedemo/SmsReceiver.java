package e.sunjunzhou.receivemessagedemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Bundle bundle=arg1.getExtras();
        Object[] objs=(Object[])bundle.get("pdus");
        SmsManager[] msgs=new SmsManager[objs.length];
        for (int i=0;i<objs.length;i++){
            msgs[i]=SmsManager.createFromPdu((byte[])objs[i]);
        }
        StringBuffer strb=new StringBuffer();
        for (SmsManager msg:msgs){
            strb.append("发短信人：\n");
            strb.append(msg.getDisplayOriginatingAddress());
            strb.append("\n短信内容：\n");
            strb.append(msg.getDisplayMessageBody());
            SimpleDateFormat myFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            long dt=msg.getTimestampMillis();
            String dtStr=myFormat.format(dt);
            strb.append("\n 接收时间:\n");
            strb.append(dtStr);
        }
        Toast.makeText(arg0,strb.toString(),Toast.LENGTH_LONG).show();

    }
}
