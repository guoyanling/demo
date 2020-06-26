package com.example.demo.service;

import com.example.demo.aop.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @DistributedLock(lockKey ="#{id}",tip = "正在获取中...")
    public String getMsg(String id) {
        try {
            // 模拟业务运行 0.5s
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "MSG: "+id;
    }

}
