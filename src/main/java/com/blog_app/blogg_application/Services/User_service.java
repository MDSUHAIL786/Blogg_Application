package com.blog_app.blogg_application.Services;

import com.blog_app.blogg_application.DTO_dataTransfer.userDto;


import java.util.List;

public interface User_service{
    //CRUD methods
    userDto createUser(userDto newUser);
    userDto updateUser(userDto existUser,Integer user_id);
    userDto getuserById(Integer user_id);
    List<userDto> getAllUsers();
    void deleteUser(Integer user_id);

}
