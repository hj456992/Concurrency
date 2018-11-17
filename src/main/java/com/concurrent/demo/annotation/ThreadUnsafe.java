package com.concurrent.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:
 * 线程不安全类的注解
 *
 * @author 侯珏
 * @create 2018-11-18 1:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadUnsafe {
    String value() default "";
}
