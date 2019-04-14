package com.zylear.j2eelab.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by xiezongyu on 2019/4/13.
 */
public class OkApplicationContextInitializer implements ApplicationContextInitializer ,EnvironmentPostProcessor {


    @Override
    public void initialize(ConfigurableApplicationContext context) {

        System.out.println("custom boot");

    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        System.out.println("env");
    }
}
