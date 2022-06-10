package com.springboot.boot.annottion;

import java.lang.annotation.*;

/**
 * 自定义注解，结合AOP实现Redis自动缓存
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    String name() default "";

    String value() default "";

    int expires() default -1;
}