package com.zylear.j2eelab.config;

import com.zylear.j2eelab.domain.Student;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@ComponentScan(basePackages = {
//        "com.zylear.j2eelab.manager"
//})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Bean(name = "apiDispatcherServlet")
//    public DispatcherServlet getApiDispatcherServlet() {
//        return super.getDispatcherServlet();
//    }


//    public ServletRegistrationBean getDispatcherServletRegistration(DispatcherServlet dispatcherServlet,
//                                                                    String[] urlMappings, String servletName) {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
//        registrationBean.addUrlMappings(urlMappings);
//        registrationBean.setName(servletName);
//        return registrationBean;
//    }


    @Bean
    public ViewResolver pageViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

//    @Bean
//    public ViewResolver pageViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/html/");
//        resolver.setSuffix(".html");
////        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }

}
