package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@IdClass(RoleUserId.class)
@Table(name = "role_user_relation")
public class RoleUser {

    @Id
    private Long roleId;
    @Id
    private Long userId;

}
