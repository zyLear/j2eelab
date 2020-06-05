package com.zylear.j2eelab.base.spi;

import java.util.ServiceLoader;

public class SpiSimple {

    public static void main(String[] args) {

        ServiceLoader<SpiSimpleInterface> load = ServiceLoader.load(SpiSimpleInterface.class);

        //已经实例化了的
        for (SpiSimpleInterface spiSimpleInterface : load) {
            System.out.println(spiSimpleInterface.handle());
        }

    }

}
