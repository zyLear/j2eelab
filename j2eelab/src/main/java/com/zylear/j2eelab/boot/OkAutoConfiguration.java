package com.zylear.j2eelab.boot;

import com.zylear.j2eelab.annotation.AssignDefault;
import com.zylear.j2eelab.bean.OkBean;
import com.zylear.j2eelab.bean.OkRegistryPostProcessor;
import com.zylear.j2eelab.boot.beanprocessor.OkBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2019/4/13.
 */
//@Configuration
@ConditionalOnProperty("zylear.enable")
public class OkAutoConfiguration {


    @Bean
    public String ss() {
        System.out.println("dsf");
        return "";
    }



    @Bean("zylear-a")
    public OkBean okClass() {
        OkBean okClass = new OkBean();
        System.out.println(okClass);
        return okClass;
    }

    @Bean("zylear-b")
    public OkBean etret() {
        OkBean okClass = new OkBean();
        System.out.println(okClass);
        return okClass;
    }

    @Bean
    public OkBeanPostProcessor sdfdf() {
        OkBeanPostProcessor okBeanPostProcessor = new OkBeanPostProcessor();
        System.out.println("sdfsdf");
        return okBeanPostProcessor;
    }


}

//class OKClass{
//    @AssignDefault
//    private String value;
//
//}
