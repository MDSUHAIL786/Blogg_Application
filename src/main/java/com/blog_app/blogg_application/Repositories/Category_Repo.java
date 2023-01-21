package com.blog_app.blogg_application.Repositories;

import com.blog_app.blogg_application.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Category_Repo extends JpaRepository<Category,Integer> {
}
