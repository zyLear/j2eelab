package com.zylear.j2eelab.base.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();

    }

}
