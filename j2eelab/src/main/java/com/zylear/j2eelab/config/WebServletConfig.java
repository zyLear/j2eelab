package com.zylear.j2eelab.config;

import com.zylear.j2eelab.domain.Student;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by xiezongyu on 2019/3/8.
 */
@Configuration
public class WebServletConfig {


    @Bean
//    @Order(Ordered.LOWEST_PRECEDENCE)
    public ServletRegistrationBean apiDispatcherServletRegistration(ApplicationContext context) {

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
//        webContext.register();
        dispatcherServlet.setApplicationContext(context);
        Student bean = (Student) context.getBean("get-bean");
        System.out.println("bean student : " + bean);

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.addUrlMappings("/api/*");
        registrationBean.setName("api_servlet");
        return registrationBean;
    }


    @Bean
    public ServletRegistrationBean pageDispatcherServletRegistration(ApplicationContext context) {

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setApplicationContext(context);

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.addUrlMappings("/page/*");
        registrationBean.setName("page_servlet");
        return registrationBean;
    }


}
