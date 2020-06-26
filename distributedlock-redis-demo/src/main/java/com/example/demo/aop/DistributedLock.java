package com.example.demo.aop;


import java.lang.annotation.*;

/**
 * DistributedLock
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    String lockKey() default "NONE";
    String tip() default "正在操作中";
}
