package com.example.demo.service;

import com.example.demo.aop.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @DistributedLock(lockKey ="#{id}")
    public String getMsg(String id) {
        try {
            // 模拟业务运行 1s
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "MSG: "+id;
    }

}
