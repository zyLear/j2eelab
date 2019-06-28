package com.zylear.j2eelab.base.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.*;

/**
 * Created by xiezongyu on 2019/6/25.
 */
public class JdkThreadPoolTest {

    public static ExecutorService executorService;

    public static void main(String[] args) {


        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        UncaughtExceptionHandler uncaughtExceptionHandler = (t, e) -> {
            System.out.println("exception :" + t.getName());
            e.printStackTrace();
        };
//        Thread.currentThread().setUncaughtExceptionHandler(uncaughtExceptionHandler);

//        System.out.println(uncaughtExceptionHandler);
        executorService = new ThreadPoolExecutor(1, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2),
                new ThreadFactoryBuilder()
                        .setDaemon(false)
                        .setNameFormat("com.qingqing.wxmgr.groupHandler")
                        .setUncaughtExceptionHandler(uncaughtExceptionHandler)
                        .build()
                ,
                (r, executor) -> System.out.println("rejected: " + r.toString()));


        for (int i = 0; i < 1; i++) {
            int finalI = i;
            Future<?> ttt = executorService.submit(new Thread() {
                @Override
                public void run() {

                    ExecutorService s = executorService;
                    System.out.println(Thread.currentThread().getName());
//                    this.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                    System.out.println(Thread.currentThread().getUncaughtExceptionHandler());
                    int j = 1;
                    while (j-- > 0) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("current:" + finalI);

                    }


                    throw new RuntimeException("ttt");
                }
            });

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                ttt.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            Integer.bitCount()
        }

//        Thread a = new Thread() {
//            @Override
//            public void run() {
//                throw new RuntimeException("dd");
//            }
//        };
//        a.setUncaughtExceptionHandler((t, e) -> {
//            System.out.println("exception :" + t.getName());
////            e.printStackTrace();
//        });
//
//        a.start();

    }


}
