package com.zylear.j2eelab.base.volatiletest;

public class ShowVolatile {

    public static volatile int a = 1;

    public static void main(String[] args) {

        int b=1;
        a = 2;

    }

}
