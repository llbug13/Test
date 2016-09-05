package com.ll.test.tiem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by LL on 2016/9/5.
 * <p/>
 * alarm是一种预先确定的时间和间隔内激活intent的方式
 * 重启会消失
 * 没有在生命周期之内
 */
public class AlarmTest {
    private void alarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        广播
        Intent intent = new Intent("alarm_action");
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//        AlarmManager.RTC_WAKEUP唤醒设备，激活intent,时间已过时立马执行
//        AlarmManager.RTC;激活intent，不唤醒设备
//        AlarmManager.ELAPSED_REALTIME经过时间不唤醒设备，激活intent
//        经过时间唤醒设备，激活intent
//        使用相同的intent，第二个会代替第一个
//        设置一次
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, alarmIntent);
//        精准的时间间隔
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, 1000, alarmIntent);
//        执行更新和会消耗电能，会限制对电池电能的影响
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, AlarmManager.INTERVAL_DAY, alarmIntent);
//        alarmManager.cancel(alarmIntent);取消
    }

}
