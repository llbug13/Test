package com.ll.test.save;

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
    }
}
