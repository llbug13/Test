package com.ll.test.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by LL on 2016/8/22.
 */
public class app extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        应用在创建时调用
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        系统资源缺乏，后台进程已经终止，前台进程依旧缺乏资源
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
//        (4.0(api level 13))
//        OnTrimMemory的参数是一个int数值，代表不同的内存状态：
//        TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
//        TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
//        TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
//        TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。
//        以上4个是4.0增加
//
//        TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
//        TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
//        TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存
//        以上3个是4.1增加
//        系统也提供了一个ComponentCallbacks2，通过Context.registerComponentCallbacks()注册后，就会被系统回调到。
//        OnLowMemory和OnTrimMemory的比较
//        1，OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
//        2，OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
//        3，通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        不会终止和重启
    }
}
