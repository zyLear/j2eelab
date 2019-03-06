package com.zylear.j2eelab.proxy;

/**
 * Created by xiezongyu on 2018/11/7.
 */
public class Enhance {

    public void before() {
        System.out.println("enhance before");
    }

    public void after() {
        System.out.println("enhance after");
    }


}
