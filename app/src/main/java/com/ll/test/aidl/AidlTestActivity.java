package com.ll.test.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.ll.test.base.BActivity;

/**
 * Created by LL on 2016/9/19.
 */
public class AidlTestActivity extends BActivity {

    private final static boolean create_flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //开启服务
        Intent intent = new Intent(this, service.getClass());
        startService(intent);

        //连接远程Service和Activity
        Intent binderIntent = new Intent(this, service.getClass());
//        binderIntent = new Intent(service.getClass().getName());
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag", create_flag);
        binderIntent.putExtras(bundle);
        bindService(binderIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private boolean mIsBind = false;
    private aidlTest aidlTest1;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlTest1 = aidlTest.Stub.asInterface(service);
            mIsBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlTest1 = null;
            mIsBind = false;
        }
    };

    private Service service = new Service() {
        aidlTest.Stub stub = new aidlTest.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            boolean flag = intent.getExtras().getBoolean("flag");
            System.out.println(flag);
            return stub;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean flag = false;
        //暂停服务
        Intent intent = new Intent(this, service.getClass());
        stopService(intent);

        //断开与远程Service的连接
        unbindService(mServiceConnection);
    }
}
