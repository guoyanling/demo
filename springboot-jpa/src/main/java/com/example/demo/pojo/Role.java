package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    private Long id;
    private String name;
    private String note;
}
