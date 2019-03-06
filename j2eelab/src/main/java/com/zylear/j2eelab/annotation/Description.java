package com.zylear.j2eelab.annotation;

import java.lang.annotation.*;

/**
 * Created by xiezongyu on 2017/10/24.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
public @interface Description {

    String desc();

    String author();

    int age() default 20;

}
