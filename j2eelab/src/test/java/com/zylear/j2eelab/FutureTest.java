package com.zylear.j2eelab;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by xiezongyu on 2017/11/28.
 */
public class FutureTest {

    ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void testFuture() {
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {


                try {
                    int i = 0;
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException();
                        }

                    }
                } catch (InterruptedException e) {

                } finally {
                    System.out.println("callable finally signal");
                }

                return null;
            }
        });

        try {
            future.get(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("task timeout signal");
            future.cancel(true);

        }


        while (true) {
            try {
                Thread.sleep(3000);
//                System.out.println(.getActiveCount());
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    @Test
    public void testFutureTwo() {
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while (i<5) {
                        Thread.sleep(2000);
                        System.out.println("sleep 2 seconds");
                        i++;
                    }
                } catch (Exception e) {

                } finally {
                    System.out.println("callable finally signal");
                }
            }

        });

        try {
            future.get(60, TimeUnit.SECONDS);
            System.out.println("end");
        } catch (Exception e) {
            System.out.println("task timeout signal");
          //  future.cancel(true);

        }


        while (true) {
            try {
                Thread.sleep(3000);
//                System.out.println(.getActiveCount());
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }


}
