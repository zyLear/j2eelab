package com.zylear.j2eelab.base.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiezongyu on 2019/6/27.
 */
public class CustomBlockingQueueImpl<E> implements CustomBlockingQueue<E> {

    private Object[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

    private ReentrantLock lock;

    private Condition notEmpty;
    private Condition notFull;


    public CustomBlockingQueueImpl(int count) {
//        this.count = count;

        this.items = new Object[count];
        lock = new ReentrantLock(true);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    private int inc(int i) {
        return (++i == items.length) ? 0 : i;
    }

    @Override
    public void put(E element) throws InterruptedException {

        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                notFull.await();
            }
            insert(element);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {

        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            return extract();
        } finally {
            lock.unlock();
        }
    }

    private void insert(E e) {
        items[putIndex] = e;
        putIndex = inc(putIndex);
        ++count;
        notEmpty.signal();
    }

    @SuppressWarnings("unchecked")
    private E extract() {
        Object item = items[takeIndex];
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        count--;
        notFull.signal();
        return (E) item;
    }


    public static void main(String[] args) throws InterruptedException {
        CustomBlockingQueue<Integer> queue = new CustomBlockingQueueImpl<>(1);
//        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI * 3000);
                        queue.put(finalI);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
        while (true) {
//                        Thread.sleep(5000);

            System.out.println(queue.take());
            System.out.println("get");
        }
    }
}
