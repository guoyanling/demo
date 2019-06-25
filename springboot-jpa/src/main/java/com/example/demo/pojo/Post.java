package com.example.demo.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Data
@ToString
@Table(name = "t_post")
public class Post {
    @Id
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    private User creator;
}
