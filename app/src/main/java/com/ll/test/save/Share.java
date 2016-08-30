package com.ll.test.save;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ll.test.app.app;

import java.util.Map;

/**
 * Created by LL on 2016/8/30.
 */
public class Share {
    private boolean save() {
        SharedPreferences sharedPreferences = app.getContext().getSharedPreferences("ll", Activity.MODE_PRIVATE);
//       默认的
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app.getContext());
//      不与其他组件共享
        Activity activity = null;
        sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        //        添加改变监听
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//可以更新ui
            }
        });
//        得到数据
        sharedPreferences.getBoolean("ll", true);
        Map<String, ?> map = sharedPreferences.getAll();
        boolean is = sharedPreferences.contains("ll");
//       编辑对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ll", true);
//        异步保持，api level 9保存的首选方法
        editor.apply();
//       同步保存,true成功，会阻止调用线程
        return editor.commit();

    }
}
