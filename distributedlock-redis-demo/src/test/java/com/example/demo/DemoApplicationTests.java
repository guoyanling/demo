package com.example.demo;


import com.example.demo.service.TestService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TestService testService;

    @SneakyThrows
    @Test
    public void test() {
        String id  = "1";
        // 模拟并发
        for (int i = 0; i < 50; i++) {
            new Thread(()-> {
                try {
                    testService.getMsg(id);
                } catch (Exception ignored) {}
            }).start();
        }
        new CountDownLatch(1).await(); //防止程序没执行完退出
    }
}
