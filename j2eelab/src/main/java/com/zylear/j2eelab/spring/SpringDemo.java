package com.zylear.j2eelab.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiezongyu on 2019/8/2.
 */
public class SpringDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-ioc.xml");
//        context.getEnvironment()
        IOCService iocService = context.getBean(IOCService.class);
        System.out.println(iocService.hello());
    }

}
