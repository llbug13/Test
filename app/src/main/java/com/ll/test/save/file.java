package com.ll.test.save;

import android.content.Context;
import android.os.Environment;

import com.ll.test.app.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by LL on 2016/8/30.
 */
public class file {
    private void file() {
        File file = app.getContext().getDir("", 1);
//        返回外部的根目录
        File file1 = app.getContext().getExternalFilesDir("");
//        Environment.getDataDirectory()
//        /Android/data/[package]/file 自己应用的目录
        Environment.getExternalStorageDirectory().getAbsolutePath();
//       ch储存应用程序文件的路径
//        可用的声音文件
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath();
        Environment.getDataDirectory();
//        可管理的内部缓存,存储不够时会被删除
        app.getContext().getCacheDir();
//        不可管理的外部缓存,不够时不会被删除
        app.getContext().getExternalCacheDir();
        try {
//            用于当前应用，指定路径分隔符会导致异常
//           out会创建或覆盖，Context.MODE_APPEND是末尾添加  Context.MODE_WORLD_READABLE Context.MODE_WORLD_WRITEABLE其他程序也可以用
            FileOutputStream fileOutputStream = app.getContext().openFileOutput("q.tmp", Context.MODE_PRIVATE);
            FileInputStream fileInputStream = app.getContext().openFileInput("q.tmp");
//            得到程序沙盒的位置
            app.getContext().getFilesDir().getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
