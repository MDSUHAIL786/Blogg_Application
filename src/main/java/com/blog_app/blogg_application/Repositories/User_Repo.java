package com.blog_app.blogg_application.Repositories;

import com.blog_app.blogg_application.Entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface User_Repo extends JpaRepository<user,Integer> {
    Optional<user> findByemail(String email);
}
