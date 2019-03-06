package com.zylear.j2eelab.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xiezongyu on 2018/8/20.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Handle {
    int id() default 0;

    String value() default "value";

}
