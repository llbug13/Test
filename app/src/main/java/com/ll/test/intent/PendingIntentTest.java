package com.ll.test.intent;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by LL on 2016/8/25.
 */
public class PendingIntentTest {
    {
//        PendingIntent可以其他程序稍晚触发的intent
//        用于将来触发的intent 如widget  和notification
//        在执行时拥有和程序自己执行他们时相同的权限和身份
        Context context = null;
        int rcode = 0;
        int flags = 0;
        Intent intent = new Intent();
//        有指定标识可以指定是否只触发一次
        PendingIntent.getActivity(context, rcode, intent, flags);
    }
}
