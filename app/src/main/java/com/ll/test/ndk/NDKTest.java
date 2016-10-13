package com.ll.test.ndk;

/**
 * Created by LL on 2016/9/21.
 */
public class NDKTest {
    /**
     * Native method implemented in C/C++
     */


    static {
        System.loadLibrary("native-lib");
    }

    public static native String stringFromJNI();

}
