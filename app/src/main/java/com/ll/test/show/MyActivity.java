package com.ll.test.show;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.ll.test.R;

public class MyActivity extends Activity {

    private static final String BUTTON_CLICK_ACTION = "com.paad.notifications.action.BUTTON_CLICK";
    private static final int NOTIFICATION_REF = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        /**
         * Listing 10-33: Using the Notification Manager
         */
        String svcName = Context.NOTIFICATION_SERVICE;

        NotificationManager notificationManager;
        notificationManager = (NotificationManager) getSystemService(svcName);

    }

    public void onL(View view) {
        triggerNotification(customNotificationWindowNotification());
    }

    private Notification simpleNotification() {
        /**
         * Listing 10-34: Creating a Notification
         */
        // Choose a drawable to display as the status bar icon
        int icon = R.mipmap.ic_launcher;
        // Text to display in the status bar when the notification is launched
        String tickerText = "Notification";
        // The extended status bar orders notification in time order
        long when = System.currentTimeMillis();

        Notification notification = new Notification(icon, tickerText, when);

        //
        return notification;
    }

    private Notification.Builder notificationBuilder() {
        /**
         * Listing 10-35: Setting Notification options using the Notification Builder
         */
        Notification.Builder builder =
                new Notification.Builder(MyActivity.this);
//        Notification.FLAG_SHOW_LIGHTS //三色灯提醒，在使用三色灯提醒时候必须加该标志符
//        Notification.FLAG_ONGOING_EVENT //发起正在运行事件（活动中）
//        Notification.FLAG_INSISTENT //让声音、振动无限循环，直到用户响应 （取消或者打开）
//        Notification.FLAG_ONLY_ALERT_ONCE //发起Notification后，铃声和震动均只执行一次
//        Notification.FLAG_AUTO_CANCEL //用户单击通知后自动消失
//        Notification.FLAG_NO_CLEAR //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
//        Notification.FLAG_FOREGROUND_SERVICE //表示正在运行的服务
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND |
                        Notification.DEFAULT_VIBRATE)
                .setSound(
                        RingtoneManager.getDefaultUri(
                                RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 0, 1);

        Notification notification = builder.getNotification();

        //
        return builder;
    }

    private Notification.Builder customLayoutNotification() {
        Notification.Builder builder =
                new Notification.Builder(MyActivity.this);

        Intent newIntent = new Intent(BUTTON_CLICK_ACTION);
//        PendingIntent pendingIntent =
//                PendingIntent.getBroadcast(MyActivity.this, 2, newIntent, 0);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(MyActivity.this, 2, newIntent, 0);
        Bitmap myIconBitmap = null; // TODO Obtain Bitmap

        /**
         * Listing 10-36: Applying a custom layout to the Notification status window
         */
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Title")
                .setContentText("Subtitle")
                .setContentInfo("Info")
                .setLargeIcon(myIconBitmap)
                .setContentIntent(pendingIntent);

        //
        return builder;
    }

    private Notification.Builder customNotificationWindowNotification() {
        Notification.Builder builder =
                new Notification.Builder(MyActivity.this);

        /**
         * Listing 10-38: Applying a custom layout to the Notification status window
         */
        RemoteViews myRemoteView =
                new RemoteViews(this.getPackageName(),
                        R.layout.my_notification_layout);
//        Intent newIntent = new Intent(BUTTON_CLICK_ACTION);
//        中的flags属性参数：
//        FLAG_ONE_SHOT 表示返回的PendingIntent仅能执行一次，执行完后自动取消
//        FLAG_NO_CREATE 表示如果描述的PendingIntent不存在，并不创建相应的PendingIntent，而是返回NULL
//        FLAG_CANCEL_CURRENT 表示相应的PendingIntent已经存在，则取消前者，然后创建新的PendingIntent
//        FLAG_UPDATE_CURRENT 表示更新的PendingIntent
//        PendingIntent newPendingIntent =
//                PendingIntent.getBroadcast(MyActivity.this, 2, newIntent, 0);
//        builder.setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker("Notification")
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle("Progress")
//                .setProgress(100, 50, false)
//                .setContentIntent(newPendingIntent)
//                .setContent(myRemoteView);
//
//
//        Notification notification = builder.getNotification();
////        notification.contentView=myRemoteView;手动设置时，必须设置contentIntent
////        notification.contentIntent = newPendingIntent;
//        /**
//         * Listing 10-39: Customizing your extended Notification window layout
//         */
//        notification.contentView.setImageViewResource(R.id.status_icon,
//                R.drawable.icon);
//        notification.contentView.setTextViewText(R.id.status_text,
//                "Current Progress:");
//        notification.contentView.setProgressBar(R.id.status_progress,
//                100, 50, false);
//
//
//        /**
//         * Listing 10-40: Adding click handlers to your customized extended Notification window layout
//         */
//
////4.0后可以加监听器
//        notification.contentView.setOnClickPendingIntent(
//                R.id.status_progress, newPendingIntent);

        /**
         * Listing 10-41: Applying a custom layout to the Notification ticker
         * .builder.setOngoing( )
         * 设置为ture，表示它为一个正在进行的通知。简单的说，当为ture时，不可以被侧滑消失。
         */
        RemoteViews myTickerView =
                new RemoteViews(this.getPackageName(),
                        R.layout.my_ticker_layout);
//        连续
//        Notification.FLAG_INSISTENT
//        持续
//        notification.flags= notification.flags|Notification.FLAG_ONGOING_EVENT
        builder.setSmallIcon(R.drawable.notification_icon)
                .setTicker("Notification", myTickerView)
                .setWhen(System.currentTimeMillis())
                .setOngoing(true)
                .setContent(myRemoteView);

        return builder;
    }

    private Notification.Builder onGoingNotification() {
        Notification.Builder builder =
                new Notification.Builder(MyActivity.this);

        RemoteViews myRemoteView =
                new RemoteViews(this.getPackageName(),
                        R.layout.my_notification_layout);

        /**
         * Listing 10-42: Setting an ongoing Notification
         * .setTicker设置滚动视图
         * builder.setTicker("状态栏上显示");// 状态栏上显示
         */
        builder.setSmallIcon(R.drawable.notification_icon)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Progress")
                .setProgress(100, 50, false)
                .setContent(myRemoteView)
                .setOngoing(true);

        return builder;
    }

    private void triggerNotification(Notification.Builder builder) {
        /**
         * Listing 10-43: Triggering a Notification
         */
        String svc = Context.NOTIFICATION_SERVICE;

        NotificationManager notificationManager
                = (NotificationManager) getSystemService(svc);

        int NOTIFICATION_REF = 1;
        Notification notification = builder.getNotification();
//        Notification notification = builder.build();
//        id相同就更新
//        更新不触发音频，震动，闪烁，Notification.FLAG_ONLY_ALERT_ONCE
//        builder.setOnlyAlertOnce(true);
//        单击删除
//        builder.setAutoCancel() Notification.FLAG_AUTO_CANCEL
//        notificationManager.cancel(NOTIFICATION_REF);
        notificationManager.notify(NOTIFICATION_REF, notification);
    }

    private Notification.Builder autoCancelNotification() {
        Notification.Builder builder =
                new Notification.Builder(MyActivity.this);

        Intent newIntent = new Intent(BUTTON_CLICK_ACTION);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(MyActivity.this, 2, newIntent, 0);
        Bitmap myIconBitmap = null; // TODO Obtain Bitmap

        /**
         * Listing 10-45: Setting an auto-cancel Notification
         */
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Title")
                .setContentText("Subtitle")
                .setContentInfo("Info")
                .setLargeIcon(myIconBitmap)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder;
    }

    private void cancelNotification(NotificationManager notificationManager) {
        /**
         * Listing 10-46: Canceling a Notification
         */
        notificationManager.cancel(NOTIFICATION_REF);

    }
}