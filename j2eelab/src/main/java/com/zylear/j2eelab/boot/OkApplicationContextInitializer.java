package com.zylear.j2eelab.boot;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by xiezongyu on 2019/4/13.
 */
public class OkApplicationContextInitializer implements ApplicationContextInitializer {


    @Override
    public void initialize(ConfigurableApplicationContext context) {

        System.out.println("custom boot");

    }
}
