package com.ll.test.java;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.MessageQueue;
import android.util.Property;

import com.ll.test.R;
import com.ll.test.app.app;
import com.ll.test.log.L;
import com.ll.test.system.SystemTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
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
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        });
//        高的优先级没有进入活动状态的时候低的优先级可能永远不会执行
        thread1.setPriority(10);

        Thread thread = new Thread(new Runnable() {
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
        thread.setPriority(4);
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
//        try {
//            Thread.sleep(1233);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("run::" + thread.getState()+thread.getPriority());
    }

    public static class Thread1 {
        public static void test_synchronized() {
            while1();

        }

        private static int i = 0;

        private static void while1() {

//            任意时刻只有一个现场进入临界区
//            final ReentrantLock reentrantLock = new ReentrantLock();
//            final Condition condition = reentrantLock.newCondition();
//            try {
////            进入条件等待
//                condition.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            释放所有的条件等待
//            condition.signalAll();
//            int i1 = 0;
//            while (i1 < 100) {
//                System.out.println(i1 + ":" + i);
//                final int finalI = i1;
//                new java.lang.Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(finalI + "print:ooi:" + i);
//                        reentrantLock.lock();
////                        try {
//////                            会锁死
////                            Thread.sleep(100);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
//                        i++;
//                        System.out.println(finalI + "print:i:" + i);
//                        reentrantLock.unlock();
//
//                    }
//                }).start();
//                i1++;
//            }
//            new java.lang.Thread(new Runnable() {
//                @Override
//                public void run() {
//                    reentrantLock.lock();
////                    try {
////                        condition.await();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                    i++;
//                    System.out.println(reentrantLock.getHoldCount() + "print:i1111:" + i);
//                    reentrantLock.unlock();
//
//                }
//            }).start();
//            System.out.println(reentrantLock.getHoldCount() + "print:2222ill:" + i);
//            new java.lang.Thread(new Runnable() {
//                @Override
//                public void run() {
//                    reentrantLock.lock();
////                    condition.signalAll();
//                    i++;
//                    System.out.println(reentrantLock.getHoldCount() + "print:2222i:" + i);
////                    不是立即执行，仅仅解除
////                    condition.signalAll();
//                    reentrantLock.unlock();
//
//                }
//            }).start();


//            线程安全的数据结构
//            Collections.synchronizedMap();
//            ConcurrentHashMap

/**
 *            线程池的使用
 * */
//            线程池的使用
//            没用可用线程可增加
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            固定数量线程的线程池
//            Executors.newFixedThreadPool()
//            单个线程的线程池
//            Executors.newSingleThreadExecutor()
//           int i1 = 0;
//            while (i1 < 100) {
//                final int finalI = i1;
//                executorService.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        i++;
//                        System.out.println(Thread.currentThread().getName() + ":" + finalI + "print:i1111:" + i);
//                    }
//                });
//                System.out.println(Thread.currentThread().getName() + ":" + ((ThreadPoolExecutor) executorService).getPoolSize());
//                i1++;
//            }

            /**
             * 预定线程池的使用
             * */
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
//            executorService.schedule()
//            单线程
//            Executors.newSingleThreadScheduledExecutor();
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
//            executorService.invokeAll();
//            Timer;
//            TimerTask
        }

        /**
         * fork-join框架
         */
        public static class ForkJoinTest {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public static void main() throws ExecutionException, InterruptedException {
//                ForkJoinPool：这个类实现ExecutorService和工作窃取算法，它对工作线程进行管理。
                long l0 = System.currentTimeMillis();
                ForkJoinPool pool = new ForkJoinPool();
                CountTask task = new CountTask(1, 1000000);

                /**采用同步方式，调用同步方法（如：invokeAll()），任务会被挂起，直到在Fork/Join的任务执行完成。
                 * 所以这种方式ForkJoinPool可以采用工作窃取算法。
                 * 异步方式，调用异步方法（如：fork()）,任务将继续执行，所以这种方式ForkJoinPool不能采用工作窃取算法。*/

                ForkJoinTask<Integer> submit = pool.submit(task);
//                join()和get()方法的区别:
//                这两个方法都是等待任务结束，获取返回结果。
//                join()，不能被中断，如果中断，方法将会抛出InterruptException异常，
//                如果任务抛出任务运行时异常，get（）方法将会抛出ExecutionException，而join会抛出RuntimeException异常。
                System.out.println("Final result:" + submit.get() + "::" + (System.currentTimeMillis() - l0) + "::" + pool.getPoolSize());
//                l0 = System.currentTimeMillis();
//                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//                int o = 0;
//                List<Callable<Integer>> list = new ArrayList(1000);
//                while (o < 1000) {
//                    final int finalO = o;
//
//                    list.add(new Callable<Integer>() {
//                        @Override
//                        public Integer call() {
//                            System.out.println("Final result:aappp" + Thread.currentThread().getName());
//                            int i = 1;
//                            int o1 = finalO * 1000;
//                            while (i < 1001) {
//                                o1 += o1 + i;
//                            }
//                            return o1;
//                        }
//                    });
//                    o++;
//                }
//
//                cachedThreadPool.invokeAll(list);
//                System.out.println("Final result:ppp                          " + submit.get() + "::" + (System.currentTimeMillis() - l0) + "::" + pool.getPoolSize());


                l0 = System.currentTimeMillis();
                int i = 1;
                int i1 = 0;
                while (i < 1000001) {
                    i1 += i;
                    i++;
                }
                System.out.println("Final result++:" + i1 + "::" + (System.currentTimeMillis() - l0));
            }
        }

        /**
         * ForkJoinTask：这个类实现Funture接口，是在ForkJoinPool执行任务的基类，
         * 所有子类需要重写compute()方法，在该方法中进行任务的划分和执行。
         * 这个类包括两个子类：RecursiveTask用于任务用返回值得场景，RecursiveAction用于没有返回值得场景。
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        static class CountTask extends RecursiveTask<Integer> {

            private static final long serialVersionUID = 1L;
            //阈值
            private static final int THRESHOLD = 10000;
            //起始值
            private int start;
            //结束值
            private int end;

            public CountTask(int start, int end) {
                this.start = start;
                this.end = end;
            }

            //            合并排序是 divide-and-conquer 算法
            @Override
            protected Integer compute() {
                boolean compute = (end - start) <= THRESHOLD;
                int res = 0;
                if (compute) {
//                    选择顺序
                    for (int i = start; i <= end; i++) {
                        res += i;
                    }
                } else {
                    //如果长度大于阈值，则分割为小任务
                    int mid = (start + end) / 2;
                    CountTask task1 = new CountTask(start, mid);
                    CountTask task2 = new CountTask(mid + 1, end);
                    //计算小任务的值-划分操作
                    task1.fork();
                    task2.fork();
                    //得到两个小任务的值-合并
                    int task1Res = task1.join();
                    int task2Res = task2.join();
                    res = task1Res + task2Res;
                }
                return res;
            }

//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            class counter extends RecursiveTask<Integer> {
//
//                @Override
//                protected Integer compute() {
//                    return null;
//                }
//            }
//
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            class counter1 extends RecursiveAction {
//
//                @Override
//                protected void compute() {
//                }
//            }


        }

        /**
         * 信号量
         */
        public static class TestSemaphore {
            public static void main() {
                // 线程池
                ExecutorService exec = Executors.newCachedThreadPool();
                // 只能5个线程同时访问
//                true表现公平性
                final Semaphore semp = new Semaphore(6);
                // 模拟20个客户端访问
                for (int index = 0; index < 20; index++) {
                    final int NO = index;
                    Runnable run = new Runnable() {
                        public void run() {
                            try {
                                // 获取许可
                                semp.acquire();
                                System.out.println("Accessing: " + Thread.currentThread().getName() + "::" + NO);
//                                Thread.sleep(100);
                                // 访问完后，释放
                            } catch (InterruptedException e) {
//                                e.printStackTrace();
                            } finally {
                                semp.release();
                                System.out.println("-----------------" + semp.availablePermits());
                            }
                        }
                    };
                    exec.submit(run);
                }

                // 退出线程池

//                exec.shutdown();

            }

//            倒计时门阀


        }

        public static class CountDownLatchTest {

            // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
            public static void main() throws InterruptedException {
                // 开始的倒数锁
                final CountDownLatch begin = new CountDownLatch(1);
                // 结束的倒数锁
                final CountDownLatch end = new CountDownLatch(10);
                // 十名选手
                final ExecutorService exec = Executors.newFixedThreadPool(10);

                for (int index = 0; index < 10; index++) {
                    final int NO = index + 1;
                    Runnable run = new Runnable() {
                        public void run() {
                            try {
                                // 如果当前计数为零，则此方法立即返回。
                                // 等待
                                begin.await();
                                Thread.sleep((long) (Math.random() * 10000));
                                System.out.println(Thread.currentThread().getName() + "No." + NO + " arrived");
                            } catch (InterruptedException e) {
                            } finally {
                                // 每个选手到达终点时，end就减一
                                end.countDown();
                            }
                        }
                    };
                    exec.submit(run);
                }
                System.out.println("Game Start");
                // begin减一，开始游戏
                begin.countDown();
                // 等待end变为0，即所有选手到达终点
                end.await();
                System.out.println("Game Over");
                exec.shutdown();
            }
        }

        /**
         * 栅栏(CyclicBarrier)
         */

        public static class TestCyclicBarrier {
            public static void main() throws InterruptedException {
                int num = 10;
                CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        System.out.println("go on together!");
                    }
                });
                for (int i = 0; i < num; i++) {
                    new Thread(new CyclicBarrierWorker(i, barrier)).start();
                }
                Thread.sleep(1000);
            }

            static class CyclicBarrierWorker implements Runnable {
                private int id;
                private CyclicBarrier barrier;

                public CyclicBarrierWorker(int id, final CyclicBarrier barrier) {
                    this.id = id;
                    this.barrier = barrier;
                }

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        System.out.println(id + " th people wait");
                        barrier.await(); // 大家等待最后一个线程到达
                        System.out.println(id + " th people wait" + Thread.currentThread().getName());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }


        }

        /**
         * 交换器
         */
        public static class ExchangerTest {

            public static void main() throws InterruptedException {
                Exchanger<List<Integer>> exchanger = new Exchanger<>();
                new Consumer(exchanger).start();
                new Producer(exchanger).start();
                Thread.sleep(1000);
            }

            static class Producer extends Thread {
                List<Integer> list = new ArrayList<>();
                Exchanger<List<Integer>> exchanger = null;

                public Producer(Exchanger<List<Integer>> exchanger) {
                    super();
                    this.exchanger = exchanger;
                }

                @Override
                public void run() {
                    Random rand = new Random();
                    for (int i = 0; i < 10; i++) {
                        list.clear();
                        list.add(rand.nextInt(10000));
                        list.add(rand.nextInt(10000));
                        list.add(rand.nextInt(10000));
                        list.add(rand.nextInt(10000));
                        list.add(rand.nextInt(10000));
                        try {
                            System.out.println(",11111 ");
                            list = exchanger.exchange(list);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

            static class Consumer extends Thread {
                List<Integer> list = new ArrayList<>();
                Exchanger<List<Integer>> exchanger = null;

                public Consumer(Exchanger<List<Integer>> exchanger) {
                    super();
                    this.exchanger = exchanger;
                }

                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            System.out.println(",adas ");
                            list = exchanger.exchange(list);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.print(list.get(0) + ", ");
                        System.out.print(list.get(1) + ", ");
                        System.out.print(list.get(2) + ", ");
                        System.out.print(list.get(3) + ", ");
                        System.out.println(list.get(4) + ", ");
                    }
                }
            }


        }
        /**ed
         *  同步队列是一种将生产者与消费者线程配对的机制。当一个线程调用SynchronousQueue的put方法时，它会阻塞直到另一个线程调用take方法为止，反之亦然。与Exchanger的情况不同，数据仅仅沿一个方向传递，从生产者到消费者。

         即使SynchronousQueue类实现了BlockingQueue接口，概念上讲，它依然不是一个队列它没有包含任何元素，它的size方法总是返回0。a
         * */

//        DelayQueue;
//        ArrayBlockingQueue;
//        MessageQueue;

    }

    public static class Io {
        //        static Byte aByte = 127;
        static char s = 0;

        public static void test() {
            Reader r;
            InputStream s1;
            FileInputStream l;
//            System.in;
            System.currentTimeMillis();

            while (s < 500) {
                System.out.println(s);
                s++;
            }
        }

        //读取raw文件
        public static void rawRead() {
            String ret = "sds另一个工作\nasdasdasd";
//            InputStream is = null;
            ByteArrayInputStream is = new ByteArrayInputStream(ret.getBytes());
            System.out.println(Inputstr2Str_Reader(is));
//            try {
////                is = app.getContext().getResources().openRawResource(R.raw.ll1);
//                int len = is.available();
//                byte[] buffer = new byte[len];
//                is.read(buffer);
//                ret = new String(buffer);
//                System.out.println(Charset.defaultCharset().name() + len + ret);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException e) {
////                    Log.e(tag, "close file", e);
//                }
//            }
        }

        public static String Inputstr2Str_Reader(InputStream in) {
            String str = "";
            try {
//                if (encode == null || encode.equals("")) {
//                    // 默认以utf-8形式
//                    encode = "utf-8";
//                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                StringBuffer sb = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    sb.append(str).append("\n");
                }
                return sb.toString();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }


}


