package com.ll.test.java;


/**
 * Created by ll on 16-11-22.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.AsyncLayoutInflater;
import android.util.Log;

import com.ll.test.internet.Internet;
import com.ll.test.log.L;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test_1 {
    static String s = "";

    public void test() {
//        ArrayList arrayList = new ArrayList();
//        arrayList.add("11");
//        arrayList.add("111");
//        arrayList.add("222");
//        String[] arrayList1 = new String[17];
//        System.out.println(new ObjectAnalyzer().toString(arrayList));
//        arrayList1 = Arrays.copyOf(arrayList1, arrayList.size());
//

//        ArrayList<String> arrayList = new ArrayList<>();
//        ArrayList arrayList1 = arrayList;
//        arrayList1.add(1);

//        this.new Test();
        Object[] objects = new Object[100];
        Integer integer = 0;
        for (int i = 0; i < objects.length; i++) {
            integer = i + 1;
            h h1 = new h(integer);
            objects[i] = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, h1);
        }
        Integer key = new Random().nextInt(objects.length) + 1;
        int result = Arrays.binarySearch(objects, key);
        if (result >= 0) {
            System.out.println(objects[result]);
        }
//        Exception exception;

//        ArrayIndexOutOfBoundsException;
        File file = new File("aada");


    }

    public void oo(A<? super A> a) {
//        超类型
        a = new A1<>();
    }

    public void oo1(A<? extends A> a) {
        A<? extends String> a1 = new A1<String>();
//        a = new A1<String>();
    }


    private void kk(A<?> a) {
//        无限制
//        a.set(new Object());
//        可以代用null
        a.set(null);
//        不能被使用
        A a1 = new A<>();
        a1.set(new Object());
//        a1带泛型的参数的方法没法使用、
//        这与是原先的（object）不一样
    }

    public static <T> T swapHelper(A<T> a) {
//        辅助方法
//        捕获A<?>
        return a.get();
    }

    public static void swap(A<?> a) {
        swapHelper(a);
    }


    public static void swap1(A a) {
        swapHelper(a);
        A<?>[] as = new A[10];
        as[1] = new A<>();
        as[1] = new A<String>();
        as[1] = new A<Integer>();
    }

    public static <T> void swapHelper2(ArrayList<A<T>> a) {
//        辅助方法
//        捕获A<?>
//        a.add(new A<Integer>());
//        return a.get();
    }

    public static void swap2(ArrayList<A<?>> a) {
        a.add(new A<Object>());
        a.add(new A<String>());
        a.add(new A<Integer>());
//        没法捕获
//        swapHelper2(a);
    }

    //    泛型测试
    public void test_fan() {
//        Object[] o = new Object[10];
//        o[1] = "s";
////        String[] strings = (String[]) o;
//        ArrayList arrayList = new ArrayList();
//        ArrayList<String> arrayList1 = arrayList;
//        arrayList1.add("");

//        String[] strings1 = new String[10];
//        Object[] objects1 = strings1;
//        objects1[1]=1;

//        String a = null;
//        A a = null;
//        if (a instanceof A1) {
//
//        }
//        泛型创造数组
//        A1<String> a2 = new A1<>();
//        String[] integers = a2.func("aa", "a", "1", "2");


//        L.out(this, integers[1]);
//        A1<String>[] a1s = new A1[10];
//        Object[] objects11 = a1s;
//        Collection<A1<String>> collection = new ArrayList<>();
//        A1<String> a11 = new A1<>();
//        A1<String> a2 = new A1<>();
////        虚拟机创建了A1<string的数组，代码中没法创建
//        alll(collection, a11, a2);
//        A1<String>[] stringA1 = tt(a11, a2);
//        Object[] objects1 = stringA1;
////        integer没起作用，书上书要要有异常没又出现，Java7.
////        没有看见异常也许是Java>7
//
//        A1<String> a41 = new A1<>();
//        A1 a42 = a41;
////        不像数组带有保护
//        a42.func(1, 3);
//
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.toArray(new String[10]);
//
//        objects1[0] = new A1<Integer>();
//        stringA1[0].func("", "");
//
//        L.out(this, new ObjectAnalyzer().toString(collection));
////        objects11[1] = "";
//        A1<String>[] a1s1 = (A1<String>[]) new A1<?>[10];
////        已经抹除
//        ArrayList<A1<String>> a1s2 = new ArrayList<A1<String>>();
////        a1s2.add(new A1<String>());
//        ArrayList a1s3 = a1s2;
//        Object[] objects = a1s;
//        a1s3.add(new Object());
//        a1s3.add(new A1<Integer>());
//        A1<Integer> a1 = (A1<Integer>) a1s3.get(1);
////        L.out(this, a1s2.get(0).getl("adasd"));
//        L.out(this, a1.getl(14564));
//        objects[1] = new A1<Integer>();
//        L.out(this, ((A1<String>) objects[1]).getl("11"));
//        a1s1[1] = new A1<>();
//        L.out(this, a1s1[1].getl("aaaaaa"));
//        a1s[1] = new A1();
//        L.out(this, a1s[1].getl("aa"));

        L.out(this, new ObjectAnalyzer().toString(String.class));
    }

    //    @SafeVarargs
    private static <T> void alll(Collection<T> collection, T... t) {
        for (T t1 : t) {
            collection.add(t1);
        }
    }

    private <T> A1<T> gett(Class<T> t) {
        try {
            T T1 = t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new A1<T>();
    }

    public void tt() {
        L.out(this, tt(2.1, 1, 3, 0));
        Integer o = 2;
        o = o + 2;
//        int i = this.<Double>tt(2.1, 1, 3, 0).byteValue();
        L.out(this, Test_1.<String>tt1("aaaa"));
        Thread.dumpStack();
    }

    private <T> T[] tt(T... t) {
        return t;
    }

    private static <T> T tt1(T t) {
        return t;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void ll() throws FileNotFoundException {
//        String[] strings =
//        Stack stack=null;
//        stack.pop();
        assert true : 1;
//        AssertionError
        try (Scanner in = new Scanner(new FileInputStream(""))) {
//            String s = new String[]{"1", "2"}[3];
//            File file = null;
//            new FileInputStream(file);
            try {
                String s = new String[]{"1", "2"}[3];
            } finally {
                System.out.println("aaaaq111aaaaaaa");
            }
        } catch (ArrayIndexOutOfBoundsException a) {
            a.getSuppressed();
            System.out.println("aaaaaaa");
        } finally {
            System.out.println("aaaaaaaaaaaaggggaaaaaaa");
        }
        System.out.println("aaggggaaaaaaa");
        try {
            new FileInputStream("aada");
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException f) {
//            final
//            f = new ArrayIndexOutOfBoundsException();
            f.printStackTrace();
            System.out.println("bbb");
        } catch (ClassCastException f) {
            f = new ClassCastException();
            Throwable throwable = f.getCause();
            Logger logger = null;
            logger.log(Level.ALL, "", f);
        }
        File file = new File("aad");
        L.out(this, file.getPath());
        System.out.println("aaaaaaa||" + file.getPath());
    }

    public void ll1() {
        File file = new File("dasd");
    }

    private class h implements InvocationHandler {
        private Object target;

        public h(Object o) {
            target = o;

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String s = target + "." + method.getName();
            if (args != null && args.length > 0) {
                s += "(" + args[0] + ")";
                System.out.println(((Comparable) target).compareTo(args[0]));
            }
            System.out.println(s);
            return method.invoke(target, args);
        }
    }

    public class Test {

        public void ll() {

            s = "";
            test();
            ObjectAnalyzer o = new ObjectAnalyzer();
        }
    }

    public static class ObjectAnalyzer {
        @SuppressWarnings("unchecked")
        public String toString(Object obj) {
            /***Converts an object to a string representation that lists all fields**/
            if (obj == null) return "null";
            if (visited.contains(obj)) return "...";
            visited.add(obj);
            Class c1 = obj.getClass();
            if (c1 == String.class) {
                return (String) obj;
            }
            if (c1.isArray()) {
                String r = c1.getComponentType() + "[]{";
                for (int i = 0; i < Array.getLength(obj); i++) {
                    if (i > 0) {
                        r += ",";
                    }
                    Object val = Array.get(obj, i);
                    if (c1.getComponentType().isPrimitive()) {
//                        基础类型
                        r += val;
                    } else {
                        r += toString(val);
                    }
                }
                return r + "}";
            }
            String r = c1.getName();
            do {
                r += "[";
                Field[] fields = c1.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                for (Field f : fields) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        if (!r.endsWith("[")) {
                            r += ",";
                        }
                        r += f.getName() + "=";
                        try {
                            Class t = f.getType();
                            Object val = f.get(obj);
                            if (t.isPrimitive()) {
                                r += val;
                            } else {
                                r += toString(val);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                r += "]";
                c1 = c1.getSuperclass();
            } while (c1 != null);
            return r;
        }

        ArrayList<Object> visited = new ArrayList<Object>();
    }
}

