package com.zylear.j2eelab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by xiezongyu on 2019/4/9.
 */

@Configuration
public class PropertiesConfig {

    //custom configuration properties


    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
    @Bean(name = "propertyPlaceholderConfigurer")
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(/*ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean*/) {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();

        placeholderConfigurer.setIgnoreResourceNotFound(false);
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        Properties properties = new Properties();
        properties.setProperty("xzyy", "xzsssdf");
        placeholderConfigurer.setProperties(properties);



        placeholderConfigurer.setLocations(new ClassPathResource("common.properties"), new ClassPathResource("application.properties"));

//        placeholderConfigurer.setLocation();

//        try {
//            Properties properties = reloadablePropertiesFactoryBean.getObject();
//            placeholderConfigurer.setProperties(properties);
//        } catch (IOException e) {
//            throw new QingQingRuntimeException("unable to find properties", e);
//        }

        return placeholderConfigurer;
    }


}
