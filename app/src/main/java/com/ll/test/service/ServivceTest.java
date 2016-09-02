package com.ll.test.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/2.
 */
public class ServivceTest extends Service {
    //    要注册
    @Override
    public void onCreate() {
//        设置到前台
//        startForeground();
        super.onCreate();
    }

    private final IBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
//        绑定activity，
        return binder;
    }

    public class MyBinder extends Binder {
        ServivceTest getService() {
            return ServivceTest.this;
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
//        等同于调用onStartCommand返回START_STICKY
        super.onStart(intent, startId);
    }

    //重新启动行为，不在建议使用onstart,并告诉系统在stopservice或stopself之前终止service，将如何重启
//    START_NOT_STICKY
//    如果系统在onStartCommand()方法返回之后杀死这个服务，那么直到接受到新的Intent对象，这个服务才会被重新创建。这是最安全的选项，用来避免在不需要的时候运行你的服务。
//
//    START_STICKY
//    如果系统在onStartCommand()返回后杀死了这个服务，系统就会重新创建这个服务并且调用onStartCommand()方法，但是它不会重新传递最后的Intent对象，系统会用一个null的Intent对象来调用onStartCommand()方法，在这个情况下，除非有一些被发送的Intent对象在等待启动服务。这适用于不执行命令的媒体播放器（或类似的服务），它只是无限期的运行着并等待工作的到来。
//
//    START_REDELIVER_INTENT
//    如果系统在onStartCommand()方法返回后，系统就会重新创建了这个服务，并且用发送给这个服务的最后的Intent对象调用了onStartCommand()方法。任意等待中的Intent对象会依次被发送。这适用于那些应该立即恢复正在执行的工作的服务，如下载文件。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        flags==START_FLAG_REDELIVERY;调用stopself之前得重启
//        START_FLAG_RETRY 返回START_STICKY，异常终止后重启
//        service是在主线程启动的，这就说明onstartcommand完成的处理四在gui主线程中完成的
//        一般是在此建立线程
//     return  START_STICKY;标准形式，在终止后重启会调用onstartcommand，重启时intent会为null,显示的调用，一般播放音乐等持续操作
//       return START_NOT_STICKY;特殊指令和操作，完成后调用stopself后就不在使用
//        return START_REDELIVER_INTENT;前两种的结合
        return super.onStartCommand(intent, flags, startId);
    }

    private void open() {
//        显示启动
        Intent intent = new Intent(getApplicationContext(), ServivceTest.class);
        startService(intent);
        Intent intent1 = new Intent("action");
        intent1.putExtra("aaaa", "aa");
        startService(intent1);
//        停止，会停止所有的intent1
        stopService(intent1);
//        如果同时有多个服务启动请求发送到onStartCommand(),不应该在处理完一个请求后调用stopSelf()；因为在调用此函数销毁service之前，可能service又接收到新的启动请求，如果此时service被销毁，新的请求将得不到处理。此情况应该调用stopSelf(int startId)
//        stopSelf(int startId)：
//        在其参数startId跟最后启动该service时生成的ID相等时才会执行停止服务。
//        stopSelf(1);
    }

    //设置为前台
    private void startPlayback(String album, String artist) {
        int NOTIFICATION_ID = 1;

        // Create an Intent that will open the main Activity
        // if the notification is clicked.
        Intent intent = new Intent(this, TestActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, intent, 0);

        // Set the Notification UI parameters
        Notification notification = new Notification(R.mipmap.ic_launcher,
                "Starting Playback", System.currentTimeMillis());
//        notification.setLatestEventInfo(this, album, artist, pi);

        // Set the Notification as ongoing
//        设置持续显示
        notification.flags = notification.flags |
                Notification.FLAG_ONGOING_EVENT;

        // Move the Service to the Foreground
//       已经吧建议是使用，使用还是会产生nop(未执行任何操作)
        startForeground(NOTIFICATION_ID, notification);
//        stopForeground(true);
    }

    //    移除到后台
    public void pausePlayback() {
        // Move to the background and remove the Notification
        stopForeground(true);
    }
}
