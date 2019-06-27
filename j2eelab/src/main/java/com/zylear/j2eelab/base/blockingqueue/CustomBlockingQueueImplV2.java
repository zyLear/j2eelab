package com.zylear.j2eelab.base.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiezongyu on 2019/6/27.
 */
public class CustomBlockingQueueImplV2<E> implements CustomBlockingQueue<E> {

    private Object[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

//    private ReentrantLock lock;

//    private Condition notEmpty;
//    private Condition notFull;

//    private Object notEmpty = new Object();
//    private Object notFull = new Object();


    public CustomBlockingQueueImplV2(int count) {
//        this.count = count;

        this.items = new Object[count];
//        lock = new ReentrantLock(true);
//        notEmpty = lock.newCondition();
//        notFull = lock.newCondition();
    }

    private int inc(int i) {
        return (++i == items.length) ? 0 : i;
    }

    @Override
    public synchronized void put(E element) throws InterruptedException {
//        lock.lock();
//        lock.lockInterruptibly();
        try {
            while (count == items.length) {
//                notFull.wait();
                wait();
            }
            insert(element);
        } finally {
//            lock.unlock();
        }
    }

    @Override
    public synchronized E take() throws InterruptedException {
//        lock.lock();
//        lock.lockInterruptibly();
        try {
            while (count == 0) {
//                notEmpty.wait();
                wait();
            }
            return extract();
        } finally {
//            lock.unlock();
        }
    }

    private void insert(E e) {
        items[putIndex] = e;
        putIndex = inc(putIndex);
        ++count;
//        notEmpty.notify();
        notify();
    }

    @SuppressWarnings("unchecked")
    private E extract() {
        Object item = items[takeIndex];
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        count--;
//        notFull.notify();
        notify();
        return (E) item;
    }


    public static void main(String[] args) throws InterruptedException {
        CustomBlockingQueue<Integer> queue = new CustomBlockingQueueImplV2<>(1);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI * 5000);
                        queue.put(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
//        while (true) {
//            System.out.println(queue.take());
//            System.out.println("get");
////            Thread.sleep(5000);
//        }
    }
}
