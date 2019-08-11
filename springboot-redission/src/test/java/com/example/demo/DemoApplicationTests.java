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
        redisTemplate.opsForValue().set("aaa","100");
        Assert.assertEquals("100", redisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void start(){
        init();
        for (int i = 0; i < 2; i++) {
            new Thread(
                    () -> {
                        System.out.println(System.currentTimeMillis()+":Hello world!I'm "+Thread.currentThread().getId());
                        this.sub();
                        System.out.println("-------------------------------");
                    }
            ).start();
        }

    }

    public void sub () {
        String lockKey = "good";
        //执行的业务代码
        for(int i=0; i < 55; i++){
            RLock lock = redisson.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("aaa").toString());
            if(stock > 0){
                redisTemplate.opsForValue().set("aaa",(stock-1)+"");
                System.out.println("test2_:lockkey:"+lockKey+",stock:"+(stock-1)+"");
            } else {
                System.out.println("没库存了，stock:"+stock);
            }
            lock.unlock(); //释放锁
        }
    }


}
