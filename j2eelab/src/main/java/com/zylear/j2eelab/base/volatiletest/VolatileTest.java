package com.zylear.j2eelab.base.volatiletest;

/**
 * Created by xiezongyu on 2019/7/9.
 */
public class VolatileTest {

    private static String sign = "i";
    private volatile static String sign1 = "i";
    private static String sign2 = "i";

    private final String a = "";

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!"stop".equals(sign)) {
                }
                System.out.println(sign);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!"stop1".equals(sign1)) {
                }
                System.out.println(sign1);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!"stop2".equals(sign2)) {
                    System.out.println();
                }
                System.out.println(sign2);

            }
        }).start();

        Thread.sleep(2000);
        sign = "stop";
        System.out.println("xx");

        while (true) {

        }
    }

}
