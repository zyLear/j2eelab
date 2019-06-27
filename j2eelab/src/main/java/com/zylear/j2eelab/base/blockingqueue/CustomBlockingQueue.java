package com.zylear.j2eelab.base.blockingqueue;

/**
 * Created by xiezongyu on 2019/6/27.
 */
public interface CustomBlockingQueue<E> {

    void put(E e) throws InterruptedException;

    E take() throws InterruptedException;

}
