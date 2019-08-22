package com.xx.core.controller;

import com.xx.common.entity.CBA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ABCController {

    @GetMapping("/aa")
    public void aa () {
//        ABC abc = new ABC();
//        abc.setAaa("aaa");
//        System.out.println(abc);
        CBA cba = new CBA();
        cba.setAaa(1);
        cba.setBbb(1);
        System.out.println(cba);
    }
}
