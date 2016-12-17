package com.ll.test.java;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by ll on 16-11-22.
 */

public class A1<T> extends A<T> {
    private T t;

    public T getT() {
        return t;
    }

    public class AA /*extends IOException*/ {
        //        @SuppressWarnings("unchecked")
        public <H extends IOException> void ll(H t, Throwable throwable) throws H {
//            不能抛出和捕获泛型对象
//            catch中不能使用泛型类型变量
//            try {
//                new FileInputStream("");
//            } catch (T e) {
//
//            }
            try {
                new FileInputStream("");
            } catch (IOException e) {
                t.initCause(e.getCause());
//                类型常量的使用是可以的
                throw t;
            }
            throw (H) throwable;


        }

    }

    private void throwAs(Throwable throwable) {
//        让编译器认为throwable是未检出异常
//        消除已检查异常的检查
//        能扔出已检测异常
//        哄骗编译器
//        不然要捕获已检测异常在扔出未检出异常
        A1.<RuntimeException>lll(throwable);
    }

    //    @SuppressWarnings("unchecked")
    public static <H extends Throwable> void lll(Throwable throwable) throws H {
        throw (H) throwable;
    }

    public T getl(T t) {
        return t;
    }


    public T[] func(T... arg) {
        return arg;
    }

    //
//    {
//        Test_1 test_1 = new Test_1();
//        Test_1.Test test = test_1.new Test();
//        test_1.test();
////        test_1.ll();
//
//    }
//    Object object = new String("11");
//    String s = (String) new Object();
    //    泛型类的在静态上下文中无效,擦除后没法使用
//    private static T t;
//    public static T s(T t) {
//        return t;
//    }

    // 子类不能抛出更通用的
    public void a() throws FileNotFoundException, IOException {
        super.a();
    }
}
