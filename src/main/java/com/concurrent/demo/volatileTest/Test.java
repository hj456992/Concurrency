package com.concurrent.demo.volatileTest;

import java.util.concurrent.*;

/**
 * 描述:
 * volatile测试
 * volatile使用场景：
 * 1、对修饰的变量写操作不依赖于当前值
 * 2、修饰的变量不包含于具有其他变量不变的式子中(不理解)
 * 因此，volatile适合作为状态标记量
 *
 * 还有一个双重检查锁
 *
 * @author 侯珏
 * @create 2018-11-18 2:48
 */
public class Test {
    private static int localTotal = 10000;
    private static String context;
    private static volatile boolean inited = false;
    private static int count1 = 0;
    private static int count2 = 0;


    static class TaskThread1 extends Thread {
        @Override
        public void run() {
            context="123";
            inited=true;
        }
    }

    static class TaskThread2 extends Thread {
        @Override
        public void run() {
            if (!inited) {
                count1 ++;
            } else {
                count2 ++ ;
            }

        }
    }

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(localTotal);
        for (int i = 0; i < localTotal ;i ++) {
            TaskThread1 thread1 = new TaskThread1();
            TaskThread2 thread2 = new TaskThread2();
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            context = null;
            inited = false;
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count1);
        System.out.println(count2);
    }
}
