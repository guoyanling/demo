package com.example.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleUserDaoTest {

    @Autowired
    private RoleUserDao roleUserDao;

    @Test
    public void delete() {
        int i = roleUserDao.deleteByRoleId(1L);
        System.out.println(i);
//        roleUserDao.deleteByRoleId(1L);
    }
}
