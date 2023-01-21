package com.blog_app.blogg_application.Repositories;

import com.blog_app.blogg_application.Entities.Category;
import com.blog_app.blogg_application.Entities.Post;
import com.blog_app.blogg_application.Entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Post_Repo extends JpaRepository<Post,Integer> {
    List<Post> findByCat(Category category);
    List<Post> findByUser(user user);

}
