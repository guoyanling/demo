package com.example.demo.dao;

import com.example.demo.pojo.Post;
import com.example.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostDaoTest {

    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void findAllByCreator() {
        User user = userDao.findTopByUsername("123");
        List<Post> list  = postDao.findAllByCreator(user);
        System.out.println(list);
    }

    @Test
    public void findAllByCreatorUsername() {
        List<Post> list  = postDao.findAllByCreatorUsername("123");
        System.out.println(list);
    }

    @Test
    public void findAllByCreatorUsername1() {
        List<Post> list  = postDao.findAllByCreator_Username("123");
        System.out.println(list);
    }


}
