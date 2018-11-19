package e.sunjunzhou.phonetosms;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Method;

public class PhoneService extends Service {
    private static  ITelephony iTelephony;
    private TelephonyManager manager;
    private String inNumber=null;
    private String message;
    private boolean disturb=false;
    public IBinder onBind(Intent arg0){
        return null;
    }
    public void onCreate(){
        try {
            MyReceiver myReceiver=new MyReceiver();
            IntentFilter filter=new IntentFilter();
            filter.addAction(Constant.ACTION_PLAY);
            filter.addAction(Constant.ACTION_PAUSE);
            registerReceiver(myReceiver,filter);
            phonelistener();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int onStartCommand(Intent intent,int flags,int startid){
        return super.onStartCommand(intent,flags,startid);
    }
    public void play(){
        disturb=true;
    }
    public void pause(){
        disturb=false;
    }
    private class MyReceiver extends BroadcastReceiver{
        public void onReceive(Context context,Intent intent){
            Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();
            message=intent.getExtras().getString("massage");
            try {
                if (Constant.ACTION_PLAY.equals(intent.getAction())){
                    PhoneService.this.play();
                }
                else if (Constant.ACTION_PAUSE.equals(intent.getAction())){
                    PhoneService.this.pause();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void sendSMS(String phonenumber,String msg){
        PendingIntent pi=PendingIntent.getActivity(this,0,new Intent(),0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(phonenumber,null,msg,pi,null);
    }
    public void phonelistener(){
        getphone();
        manager.listen(new PhoneStateListener(){
            public void onCallStateChanged(int state,String incomingNumber){
                super.onCallStateChanged(state,incomingNumber);
                inNumber=incomingNumber;
                switch (state){
                    case 1:
                        try {
                            if (disturb){
                                iTelephony.endCall();
                                Toast.makeText(getApplicationContext(),"endcall:"+incomingNumber,Toast.LENGTH_SHORT).show();
                                sendSMS(inNumber,"<PhoToSms自动回复>\n"+message);
                                showNotification();
                            }
                        }catch (RemoteException e){
                            e.printStackTrace();
                        }
                        break;
                        default:break;
                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }
    public void getphone(){
        manager=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        Class<TelephonyManager>c=TelephonyManager.class;
        Method getITelephonyMethod=null;
        try {
            getITelephonyMethod=c.getDeclaredMethod("getITelephony",(Class[])null);
            getITelephonyMethod.setAccessible(true);
            iTelephony=(ITelephony)getITelephonyMethod.invoke(manager,(Object[])null);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(){
        NotificationManager notificationManager=(NotificationManager)getBaseContext().getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new Notification(R.drawable.ic_launcher_background,"PhoneToSms",System.currentTimeMillis());
        notification.flags=Notification.FLAG_SHOW_LIGHTS;
        notification.defaults=Notification.DEFAULT_LIGHTS;
        notification.ledARGB= Color.BLUE;
        String contentText;
        String contentTile="PhoneToSms提醒你：";
        contentText="亲，"+inNumber+"刚才来电话啦，系统已自动短信回复了！";
       // notification.setLatestEventInfo(getBaseContext(),contentTile,contentText,null);//方法过时


        Builder builder1=new Builder(PhoneService.this);
        builder1.setSmallIcon(R.drawable.ic_launcher_background);
        builder1.setTicker("显示第二个通知");
        builder1.setContentTitle("通知");
        builder1.setContentText("点击查看详细信息");
        builder1.setWhen(System.currentTimeMillis());
        builder1.setDefaults(Notification.DEFAULT_ALL);
        Notification notification1=builder1.build();
        notificationManager.notify(123,notification);


        notificationManager.notify(0,notification);

    }
    public void clearNotification(){
        NotificationManager notificationManager=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }
}
