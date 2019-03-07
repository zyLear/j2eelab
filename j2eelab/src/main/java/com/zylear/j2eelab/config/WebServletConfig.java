package com.zylear.j2eelab.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {
//        "com.zylear.j2eelab"
//})
public class WebServletConfig /*extends WebServletConfigBase */ {

//    @Bean(name = "apiDispatcherServlet")
//    public DispatcherServlet getApiDispatcherServlet() {
//        return super.getDispatcherServlet();
//    }

    @Bean
    public ServletRegistrationBean apiDispatcherServletRegistration() {

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register();
        dispatcherServlet.setApplicationContext(webContext);

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.addUrlMappings("/api/*");
        registrationBean.setName("api_servlet");
        return registrationBean;
    }

//    public ServletRegistrationBean getDispatcherServletRegistration(DispatcherServlet dispatcherServlet,
//                                                                    String[] urlMappings, String servletName) {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
//        registrationBean.addUrlMappings(urlMappings);
//        registrationBean.setName(servletName);
//        return registrationBean;
//    }

}
