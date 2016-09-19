package com.ll.test.version;

import android.app.Fragment;
import android.os.Build;

/**
 * Created by LL on 2016/9/19.
 */
public class VersionTest {

    private void is() {
//        当前版本
        int i = Build.VERSION.SDK_INT;
    }

    //异常检测是否有某个类，异常和反射都是慢操作
    private static void chech() throws NoClassDefFoundError {
        boolean is = Fragment.class != null;
    }

}
