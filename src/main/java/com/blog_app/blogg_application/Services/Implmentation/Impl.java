package com.blog_app.blogg_application.Services.Implmentation;

import com.blog_app.blogg_application.DTO_dataTransfer.userDto;
import com.blog_app.blogg_application.Entities.user;
import com.blog_app.blogg_application.Exception.ResourceNotFoundException;
import com.blog_app.blogg_application.Repositories.User_Repo;
import com.blog_app.blogg_application.Services.User_service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class Impl implements User_service{

    @Autowired
    private User_Repo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public userDto createUser(userDto newUser) {
        user user=userDtoTouser(newUser);
        user savedUser=this.userRepo.save(user);
        return userTouserDto(savedUser);
    }

    @Override
    public userDto updateUser(userDto newUser, Integer user_id) {
        user user =this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("user","id",user_id));
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setAbout(newUser.getAbout());
        user updated =this.userRepo.save(user);
        return userTouserDto(updated);
    }

    @Override
    public userDto getuserById(Integer user_id){
        user user =this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("user","id",user_id));
        return userTouserDto(user);
    }

    @Override
    public List<userDto> getAllUsers() {
        List<user> allUsers=this.userRepo.findAll();
        List<userDto> dtoList=allUsers.stream().map(user->userTouserDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void deleteUser(Integer user_id) {
        user user =this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("user","id",user_id));
        this.userRepo.delete(user);
    }

    //change user to userDto
    //below method to convert one object to another is not good as if we have more fields then this
    // is unuseful, so we use model mapper to convert one object to another

    public userDto userTouserDto(user user){
        userDto userDto=this.modelMapper.map(user,userDto.class);

//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

    //change userDto to user
    public user userDtoTouser(userDto userDto){
        user convertedUser=this.modelMapper.map(userDto,user.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return convertedUser;
    }

}
