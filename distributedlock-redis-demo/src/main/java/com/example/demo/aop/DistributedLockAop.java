package com.example.demo.aop;


import com.example.demo.utils.RedissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class DistributedLockAop {

    @Autowired
    private RedissionUtil redissionUtil;

    @Around("@annotation(com.example.demo.aop.DistributedLock)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        String lockKey = (String) AnnotationResolver.newInstance().resolver(joinPoint, distributedLock.lockKey());
        String failGetLockTip = (String) AnnotationResolver.newInstance().resolver(joinPoint, distributedLock.tip());

        // 可自定义规则
        lockKey= "LOCK:"+method.getName()+":"+lockKey;
        RLock lock = redissionUtil.getRLock(lockKey);
        boolean locked = false;
        try {
            locked = getLock(lock, lockKey);
            if (!(locked)) {
                log.info("已经有人占用了锁...");
                throw new InterruptedException(failGetLockTip);
            }
            log.info("获取到锁...,{}",lockKey);
            return joinPoint.proceed();
        } finally {
            try {
                if (locked) {
                    lock.unlock();
                }
            } catch (Exception ignore) {}
        }
    }

    private boolean getLock(RLock rLock, String lockKey) {
        try {
            // leaseTime: 锁有效时间 防止锁一直被占用
            // waitTime: 等待时间 没有获取到锁继续等待的时间
            return rLock.tryLock(1, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Fail add lock,  key: [{}] ", lockKey, e);
            return false;
        }
    }
}
