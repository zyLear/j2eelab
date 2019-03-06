package com.zylear.j2eelab.annotation;

import java.lang.annotation.*;

/**
 * Created by xiezongyu on 2018/11/9.
 */

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenericRequest {
//    String value() default "";
}
