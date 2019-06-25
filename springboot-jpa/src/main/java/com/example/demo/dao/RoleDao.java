package com.example.demo.dao;

import com.example.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {
    @Query("SELECT R FROM Role R ,RoleUser RU WHERE R.id = RU.roleId AND RU.userId = :userId")
    List<Role> findRolesByUser(@Param("userId") Long userId);
}
