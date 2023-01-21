package com.blog_app.blogg_application.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title")
    private String categoryTitle;

    @Column(name = "Description")
    private String categoryDesc;

    @OneToMany(mappedBy = "cat",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

}
