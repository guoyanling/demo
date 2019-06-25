package com.example.demo.dao;

import com.example.demo.pojo.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleUserDao extends JpaRepository<RoleUser,Long> {

    @Modifying
    @Transactional
    int deleteByRoleId(Long roleId);

    @Modifying
    @Transactional
    int deleteByUserId(Long userId);
}
