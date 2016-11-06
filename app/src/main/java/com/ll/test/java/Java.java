package com.ll.test.java;

import java.util.Random;

/**
 * Created by ll on 16-11-4.
 */

public class Java {
    String java1 = new Java1().ll;

    {
        new Java1().ll1();
        s = "aaaaa";
        print("aaaaaa");
    }

    private String s = getS();

    {
        print("bbbbbb");
        print(s);
    }

    public Java() {
        print("after");
    }

    public static int ll() {
        print("llllll");
        return 3;
    }

    private static String getS() {
        print("befor");
        return "kkkk";
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    private static void print(String msg) {
        System.out.println(msg);
    }
}
