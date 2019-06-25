package com.example.demo.pojo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "t_factory_user_relation")
public class FactoryUser {

    @Id
    private Long id;
    private Long factoryId;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private User user;


}
