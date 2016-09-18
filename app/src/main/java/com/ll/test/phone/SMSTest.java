package com.ll.test.phone;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.ll.test.base.BActivity;

import java.util.ArrayList;

/**
 * Created by LL on 2016/9/18.
 * 短信
 */
public class SMSTest extends BActivity {

    private void sms() {
//        发送短信界面
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:13125"));
        startActivity(intent);
//        发送MMS
        Uri uri = Uri.parse("content://media/external/images/media/1");
        Intent intent1 = new Intent(Intent.ACTION_SEND, uri);
        intent1.putExtra("sms_body", "内容");
        intent1.putExtra("address", "13131231");
        intent1.putExtra(Intent.EXTRA_STREAM, uri);
        intent1.setType("image/jpeg");
        startActivity(intent);


        SmsManager smsManager = SmsManager.getDefault();
//        第二个是服务中心，null默认中心
//        PendingIntent sentIntent, 发送成功，或失败
        Intent intent2 = new Intent("com.paad.smssnippets.DELIVERED_SMS_ACTION");
        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent deliveryIntent,接受到后触发
        Intent intent3 = new Intent("com.paad.smssnippets.SENT_SMS_ACTION");
        PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
//                    一次成功
                    case RESULT_OK:
                        break;
//                    普通传输失败
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
//                    电话无线信号被关闭
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
//                    pdu（协议数据单元）失败
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
//                    没有可用的手机服务
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        break;

                }
            }
        }, new IntentFilter("com.paad.smssnippets.DELIVERED_SMS_ACTION"));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        }, new IntentFilter("com.paad.smssnippets.SENT_SMS_ACTION"));
//        内容限制160个字符
        ArrayList<String> list = smsManager.divideMessage("");
        ArrayList<PendingIntent> sen = new ArrayList<>();
        for (String s : list) {
            PendingIntent pendingIntent = null;
            sen.add(pendingIntent);
        }
        smsManager.sendMultipartTextMessage("1231",
                null,
                list,
                sen,
                null);
        smsManager.sendTextMessage("13123", null, "内容", sentIntent, deliveryIntent);
        smsManager.sendDataMessage("131", null, (short) 80, "".getBytes(), null, null);
    }

    private BroadcastReceiver readSMS = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            "android.provider.Telephony.SMS_RECEIVED";
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdu");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages) {
                    String msg = message.getMessageBody();
                    long when = message.getTimestampMillis();
                    String from = message.getOriginatingAddress();
//                   byte[] data= message.getUserData();数据消息
                }
            }
        }
    };
}
