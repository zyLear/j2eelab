package com.zylear.j2eelab.base.interrupt;

/**
 * Created by xiezongyu on 2019/6/28.
 */
public class InterrupterDemo {


    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
//                    System.out.println(this.isInterrupted());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("interrupt:" + thread.isInterrupted());
        System.out.println("alive:" + thread.isAlive());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("alive:" + thread.isAlive());

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
