package com.monstertechno.moderndashbord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MessageRecive extends BroadcastReceiver {

    private static final String SMS_RECIEVER="android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
  public   String msg , phoneNo= "";

    public void onReceive(Context context, Intent intent) {

       Log.i(TAG,"Intent Recived :"+ intent.getAction());
       if (intent.getAction()== SMS_RECIEVER){
           Bundle dataBundle = intent.getExtras();
           if (dataBundle != null){
               Object[] mypdu = (Object[]) dataBundle.get("pdus");
               final SmsMessage [] message = new SmsMessage[mypdu.length];
                 for (int i =0 ; i< mypdu.length;i++){

                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                         String format = dataBundle.getString("format");
                         message[i]= SmsMessage.createFromPdu((byte[]) mypdu[i], format);
                     }
                     else {
                         message[i]=SmsMessage.createFromPdu((byte[]) mypdu[i]);
                     }
                     msg = message[i].getMessageBody();
                     phoneNo=message[i].getOriginatingAddress();
                 }GLocation_Activity.Messagex=msg;
               Toast.makeText(context, "Successful Message recieved " + GLocation_Activity.Messagex+ " \n the Number :" + phoneNo, Toast.LENGTH_SHORT).show();

           }
       }
    }
}
