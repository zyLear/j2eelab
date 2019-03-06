package com.zylear.j2eelab.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2019/3/1.
 */
@Configuration
public class DisconfPropertyConfig {

//    @Bean(name = "propertyPlaceholderConfigurer")
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean){
//        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
//
//        placeholderConfigurer.setIgnoreResourceNotFound(false);
//        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
//
//        try {
//            Properties properties = reloadablePropertiesFactoryBean.getObject();
//            placeholderConfigurer.setProperties(properties);
//        } catch (IOException e) {
//            throw new QingQingRuntimeException("unable to find properties", e);
//        }
//
//        return placeholderConfigurer;
//    }
//
}
