package com.ll.test.strict;

import android.os.StrictMode;

/**
 * Created by LL on 2016/9/19.
 * 性能优化
 */
public class StrictTest {

    private void i() {
//        detectAll()平台所有监控
//        penalty对应方式
//        ui
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy
                .Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDialog()////打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
                .build());
//        内存泄漏，虚拟机
        StrictMode.setVmPolicy(new StrictMode.VmPolicy
                .Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy
                .Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());

//      重新设置
        StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy
                .Builder(old)
                .permitDiskWrites()
                .build());
//        　　doCorrectStuffThatWritesToDisk();
        StrictMode.setThreadPolicy(old);
    }
}
