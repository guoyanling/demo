package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

    }

    public void init() {
        //设置一个key，aaa商品的库存数量为100
        redisTemplate.opsForValue().set("aaa", "5");
        Assert.assertEquals("5", redisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void start() {
        init();
        sub();

    }

    public void sub() {
        String lockKey = "good";
        //执行的业务代码
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        RLock lock = redisson.getLock(lockKey);
//                        lock.lock(120, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
                        boolean res = false;
                        try {
                            res = lock.tryLock(100, 10, TimeUnit.SECONDS);
                            if (res) {
                                try {
                                    int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());
                                    if (stock > 0) {
                                        redisTemplate.opsForValue().set("aaa", (stock - 1) + "");
                                        System.out.println("Thread:" + Thread.currentThread().getId() + ":test2_:lockkey:" + lockKey + ",stock:" + (stock - 1) + "");
                                    } else {
                                        System.out.println("没库存了，stock:" + stock);
                                    }
                                } finally {
                                    lock.unlock();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();

        }
    }


}
