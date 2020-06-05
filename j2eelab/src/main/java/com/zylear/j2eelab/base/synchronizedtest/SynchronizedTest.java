package com.zylear.j2eelab.base;

public class SynchronizedTest {

    public static void main(String[] args) {

    }

    public static synchronized void f1() {

    }

    public synchronized void f2() {

    }

    public void f3() {
        synchronized (this) {

        }
    }

    public void f4() {
        synchronized (SynchronizedTest.class) {

        }
    }

}
