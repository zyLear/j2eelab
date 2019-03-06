package com.zylear.j2eelab.example;

import com.zylear.j2eelab.annotation.Handle;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * Created by xiezongyu on 2018/8/20.
 */
@Handle(id = 4,value = "ddd")
public class TestInnerClass{

    private String value;


    @PostConstruct
    public void init() {

    }

    @Handle
    public TestInnerClass(String value) {
        this.value = value;
    }

    @Handle
    public TestInnerClass() {

    }

    public void show() {
        System.out.println("show inner");
    }

    @Bean
    public String string() {
        return "";
    }
}
