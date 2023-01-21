package com.blog_app.blogg_application;

import com.blog_app.blogg_application.Repositories.User_Repo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BloggApplicationTests {
    @Autowired
    private User_Repo repo;

    @Test
    void contextLoads() {
    }

    public void Test(){
        System.out.print(repo.getClass()+" "+repo.getClass().getName()+" "+repo.getClass().getPackage());
    }
}
