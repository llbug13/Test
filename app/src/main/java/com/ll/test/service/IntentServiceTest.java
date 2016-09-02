package com.ll.test.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by LL on 2016/9/2.
 * 实现一组任务
 */
public class IntentServiceTest extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public IntentServiceTest(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
//        创建时的操作
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//发生在后台线程中
//        耗时任务在这里执行
//        逐个处理intent，完成后会终止自己
    }
}
