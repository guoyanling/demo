package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UserDao {

    public User getUser(int id) {
        return new User(id, "nocache"+id);
    }

}
