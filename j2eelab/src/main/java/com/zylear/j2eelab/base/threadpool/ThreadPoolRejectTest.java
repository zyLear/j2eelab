package com.zylear.j2eelab.base.threadpool;

import java.util.concurrent.*;

public class ThreadPoolRejectTest {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory());

        executorService.submit(new CustomRunnable());
        executorService.submit(new CustomRunnable());
        try {
            executorService.submit(new CustomRunnable());
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("eee");
    }

    public static class CustomRunnable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
