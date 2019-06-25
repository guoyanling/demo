package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {

    @Query("select u from User u ,RoleUser ru where u.id = ru.userId and ru.roleId = :roleId")
    List<User> findUsersByRole(@Param("roleId") Long roleId);

    User findTopByUsername(String username);

}
