package com.zylear.j2eelab.boot.beanprocessor;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.google.common.base.Preconditions;
import com.zylear.j2eelab.annotation.AssignDefault;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiezongyu on 2019/4/14.
 */
public class OkBeanPostProcessor implements BeanPostProcessor/*, PriorityOrdered*/ {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if ("zylear-a".equals(beanName)) {
            System.out.println("d");
        }

        if ("zylear-b".equals(beanName)) {
            System.out.println("b");
        }
        Class clazz = bean.getClass();
        for (Field field : findFields(clazz)) {
            AssignDefault annotation = AnnotationUtils.getAnnotation(field, AssignDefault.class);
            if (annotation == null) {
                return bean;
            }


//            ReflectionUtils.makeAccessible(field);
//            ReflectionUtils.setField(field, bean, config);
            field.setAccessible(true);
            try {
                field.set(bean, "custom");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return bean;
    }

    private Iterable<? extends Field> findFields(Class clazz) {
        final List<Field> res = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

//    @Override
//    public int getOrder() {
//        //make it as late as possible
//        return Ordered.LOWEST_PRECEDENCE;
//    }

}
