package com.ll.test.show;

        import android.annotation.TargetApi;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.content.Context;
        import android.graphics.Color;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.os.Build;

        import com.ll.test.R;

/**
 * Created by LL on 2016/9/5.
 */
public class NotificationTest {
    private void show(Context context) {
//        创建，修改现有的，删除
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher, "t", System.currentTimeMillis());
//        大于1图标就变成数字
        notification.number++;
//        设置默认
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        设置声音
        notification.sound = uri;
//        震动
//        <uses-permission android:name="android.permission.VIBRATE" />
        notification.vibrate = new long[]{1000, 1000, 1000};
//        设置呼吸灯的颜色，找最近的
        notification.ledARGB = Color.RED;
//        设置频率和方式，都设置成0就不闪烁，0，1事打开呼吸灯
        notification.ledOffMS = 0;
        notification.ledOnMS = 1;
        notification.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showB(Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.
                setSmallIcon(R.mipmap.ic_launcher).
                setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE)
                .setSound(
                        RingtoneManager.getDefaultUri(
                                RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 0, 1);
        Notification notification = builder.getNotification();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
