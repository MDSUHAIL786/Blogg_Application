package com.blog_app.blogg_application.security;

import com.blog_app.blogg_application.Entities.user;
import com.blog_app.blogg_application.Exception.ResourceNotFoundException;
import com.blog_app.blogg_application.Repositories.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    User_Repo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by email
        user u=userRepo.findByemail(username).orElseThrow(()->new ResourceNotFoundException("username","email :"+username,0));
        return u ;
    }
}
