package com.zylear.j2eelab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by xiezongyu on 2019/4/9.
 */
@Configuration
public class TestConfig {

    @Value("${zylear-test}")
    private String xx;

    @Value("${xzyy}")
    private String xxt;

    @Bean
    public String sdsfdfdf() {

//        System.out.println(System.getProperty("classpath"));
//        System.out.println(System.getProperty("CLASSPATH"));
//        System.out.println(System.getProperty("PATH"));
//        System.out.println(System.getProperty("path"));
        System.out.println("start ======================================");

        System.out.println(this.getClass().getClassLoader().getResource("/"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));

        Map<String, String> map = System.getenv();
        for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext(); ) {
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }
        System.out.println("end ======================================");

        System.out.println(xxt);
        System.out.println(xx);
        return "";
    }
}
