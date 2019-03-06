package com.zylear.j2eelab.aspect;

/**
 * Created by xiezongyu on 2018/11/7.
 */
public class AspectjTest {

    public void show() {
        System.out.println("show somthing");
    }

    public static void main(String[] args) {
        AspectjTest aspectjTest = new AspectjTest();
        aspectjTest.show();
    }

}
