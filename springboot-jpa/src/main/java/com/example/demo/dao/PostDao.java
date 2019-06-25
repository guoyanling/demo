package com.example.demo.dao;

import com.example.demo.pojo.Post;
import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post,Long> {

    List<Post> findAllByCreator(User user);

    List<Post> findAllByCreatorUsername(String username);

    List<Post> findAllByCreator_Username(String username);


}
