package com.ll.test.java;

import android.util.Property;

import com.ll.test.log.L;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.WeakHashMap;

/**
 * Created by ll on 16-11-30.
 */

public class Test_data {
    //    Queue
//    ArrayDeque;
//    AbstractQueue
//    ArrayList;
//    HashMap
//    Enumeration
//    LinkedList
//    Object
//    优先级队列，2叉树
//    PriorityQueue
    {
        new HashSet(1);
        new TreeSet<>();
//        HashMap<String, String> hashMap = new LinkedHashMap<>(){
//            @Override
//            protected boolean removeEldestEntry(Entry eldest) {
//                return super.removeEldestEntry(eldest);
//            }
//        };
//        int i = 0, i1 = 0;
//        while (i < 100) {
//            hashMap.put("t" + i, "ppp" + i);
//            i++;
//        }
//        Iterator iterator1 = hashMap.entrySet().iterator();
//        Iterator iterator = hashMap.keySet().iterator();
//        hashMap.put("t" + 44,"kkkk");
//        while (iterator1.hasNext()) {
//            i1++;
//            L.out(this, "ada" + iterator1.next());
//        }
//        for (String s : hashMap.values()) {
//            L.out(this, s);
//        }
//        L.out(this, System.identityHashCode(this));
//        L.out(this, hashCode());
//        List;
//        Set;
//        ArrayList;
//        HashSet hashtable = new HashSet();
//        Hashtable map = null;
//        map.elements()
//        hashtable.retainAll();
//        Collections.checkedCollection();
//        Enumeration;
//        Enum
//        IdentityHashMap
//        new EnumSet<>();
//        EnumMap;
//        Object
//        IdentityHashMap
//        LinkedList linkedList = new LinkedList();
//        linkedList.pop();
//        WeakReference;
//        WeakHashMap;
//        LinkedHashSet
    }


    public static class Sieve {

        public static void main() {
            int n = 2_000_0000;
            long start = System.currentTimeMillis();
            BitSet b = new BitSet(n + 1);
            int i;
            for (i = 2; i <= n; i++)
                b.set(i);
            i = 2;
            int count = 0;
            while (i * i <= n) {
                if (b.get(i)) {
                    count++;
                    int k = 2 * i;
                    while (k <= n) {
                        b.clear(k);
                        k += i;
                    }
                }
                i++;
            }
            while (i <= n) {
                if (b.get(i))
                    count++;
                i++;
            }
            long end = System.currentTimeMillis();
            System.out.println(count + " primes");
            System.out.println((end - start) + " milliseconds");
//            for (i = 0; i <= 100; i++) {
//                if (b.get(i)) {
//                    System.out.println("ccc" + i);
//                }
//
//            }

        }

        public static void main1() {
            int n = 2_000_0000;
            long start = System.currentTimeMillis();
            BitSet b = new BitSet(n + 1);
            int i = 2;
//            for (i = 2; i <= n; i++)
//                b.set(i);
            int count = 0;
            while (i * i <= n) {
                if (!b.get(i)) {
                    count++;
                    int k = 2 * i;
                    while (k <= n) {
                        b.set(k);
                        k += i;
                    }
                }
                i++;
            }
            while (i <= n) {
                if (!b.get(i))
                    count++;
                i++;
            }
            long end = System.currentTimeMillis();
            System.out.println(count + " primes");
            System.out.println((end - start) + " milliseconds");
//            for (i = 0; i <= 100; i++) {
//                if (b.get(i)) {
//                    System.out.println("ccc" + i);
//                }
//
//            }

        }

    }


    //     线程测试
    public static void thread() {
        System.out.println(Thread.currentThread().getName());

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
//                try {
////                    等待时间或指令终止
//                    Thread.currentThread().join(123);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("run::" + Thread.currentThread().getState());
            }
        });
        thread.start();
//        停止线程，过时，使用的是抛线程
//        thread.stop();
//        过时，暂停
//        thread.suspend();
//        过时，恢复，跟上面的方法相对应的使用
//        thread.resume();
//        try {
//            thread.join(10010);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.sleep(1233);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run::" + thread.getState()+thread.getPriority());
    }

}
