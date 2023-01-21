package com.blog_app.blogg_application.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Post_Id")
    private Integer id;

    @Column(name = "post_Title",nullable = false,length = 100)
    private String title;

    @Column(length = 10000000)
    private String content;

    private String img;

    private Date addedDate;

    @ManyToOne
    private Category cat;

    @ManyToOne
    private user user;

    @OneToMany(mappedBy = "p",cascade = CascadeType.ALL)
    private List<Comment> comment=new ArrayList<>();



}
