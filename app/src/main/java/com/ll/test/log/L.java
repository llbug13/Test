package com.ll.test.log;

import android.util.Log;

/**
 * Created by LL on 2016/8/22.
 */
public class L {
    private final static boolean IS_BUG = true;

    public static void i(String msg) {
        if (IS_BUG) {
            Log.e("----------<>----------", msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_BUG) {
            Log.e("----------<" + tag + ">----------", msg);
        }
    }
}
