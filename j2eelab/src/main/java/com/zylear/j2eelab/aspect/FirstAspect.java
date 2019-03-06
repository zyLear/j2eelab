package com.zylear.j2eelab.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/11/9.
 */
@Aspect
@Component
public class FirstAspect {

//    @Pointcut("@annotation(com.zylear.j2eelab.annotation.GenericRequest)")
//    public void GenericRequest() {
//    }

    @Before("@annotation(com.zylear.j2eelab.annotation.GenericRequest)")
    public void previous() {

        System.out.println("tttttttttttttttttttttttttt before");

    }

    @After("execution(* com.zylear.j2eelab.controller.FirstController.first(..))")
    public void testP() {
        System.out.println("tttttttttttttttttttt after");
    }





}
