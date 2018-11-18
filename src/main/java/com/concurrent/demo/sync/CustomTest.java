package com.concurrent.demo.sync;

import java.util.concurrent.*;

/**
 * 描述:
 * 自定义测试synchronized关键字使用
 *
 * @author 侯珏
 * @create 2018-11-18 1:43
 */
public class CustomTest {
    static class ProcessTask implements Runnable {
        private int taskId;

        public ProcessTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            int count = 0;
            for (int i = 0; i < 10; i ++) {
                count++;
                System.out.println("TaskId = " + taskId + ", count = " + count);
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 64, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        CountDownLatch countDownLatch = new CountDownLatch(11);
        for (int i = 0; i < 10; i ++) {
            final int taskId = i;
            executor.execute(() -> {
                new ProcessTask(taskId).run();
                System.out.println("taskId = " + taskId + "执行完毕");
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {

        }
        executor.shutdown();
        System.out.println("执行完毕");
    }


}
