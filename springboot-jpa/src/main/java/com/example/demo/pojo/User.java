package com.example.demo.pojo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "t_user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String phone;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private FactoryUser fu;

}
