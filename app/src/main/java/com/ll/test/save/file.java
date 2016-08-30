package com.ll.test.save;

import android.os.Environment;

import com.ll.test.app.app;

import java.io.File;

/**
 * Created by LL on 2016/8/30.
 */
public class file {
    private void file() {
        File file = app.getContext().getDir("", 1);
//        返回外部的根目录
        File file1=app.getContext().getExternalCacheDir();
//        Environment.getDataDirectory()
//        /Android/data/[package]/file 自己应用的目录
        Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
