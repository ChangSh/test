package com.chang.own;

import java.util.concurrent.*;

/**
 * @author: changsh
 * @date: 2018/12/13 0013
 **/
public class MyMain {
    static ThreadPoolExecutor executors = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static void add(Integer a) {
        for (int i = 0; i < 10; i++) {
            a++;
            System.out.println(Thread.currentThread().getName() + "........." + a);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        final Integer a = 0;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            try {
                executors.execute(new Runnable() {
                    @Override
                    public void run() {
                        add(a);
                    }
                });
            } finally {
                countDownLatch.countDown();
            }
        }
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end" + a);
    }


//    public static void main(String[] args) {
//        Integer a = 0;
//        MyTask1 t;
//        for (int i = 0; i < 10; i ++) {
//            t = new MyTask1(a);
//            t.run();
//        }
//        System.out.println(Thread.currentThread().getName() + "-" + a);
//    }
}
