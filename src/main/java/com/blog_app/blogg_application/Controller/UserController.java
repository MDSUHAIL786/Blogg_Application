package com.blog_app.blogg_application.Controller;

import com.blog_app.blogg_application.DTO_dataTransfer.ApiResponse;
import com.blog_app.blogg_application.DTO_dataTransfer.userDto;
import com.blog_app.blogg_application.Services.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private User_service user_service;
    //post
//    @PostMapping("/")
//    public ResponseEntity<userDto> createUser(@RequestParam(required = false) Integer id,@Valid @RequestParam(required = true) String name,@Valid @RequestParam String email,@Valid @RequestParam String password,@Valid @RequestParam(required = false) String about){
//        userDto user= new userDto();
//        user.setName(name);
//        user.setId(id);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setAbout(about);
//        return new ResponseEntity<>(this.user_service.createUser(user), HttpStatus.CREATED);
//    }
    @PostMapping("/")
    public ResponseEntity<userDto> createUser(@Valid @RequestBody userDto newUser){
        userDto created=this.user_service.createUser(newUser);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }


    //put
    @PutMapping("/{id}")
    public ResponseEntity<userDto> UpdateUser(@Valid @RequestBody userDto updatedUser, @PathVariable Integer id){
        return ResponseEntity.ok(this.user_service.updateUser(updatedUser,id));
    }
    //get
    @GetMapping("/{id}")
    public ResponseEntity<userDto> getUser(@PathVariable("id") Integer userId){
        return ResponseEntity.ok(this.user_service.getuserById(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<userDto>> getUser(){
        return ResponseEntity.ok(this.user_service.getAllUsers());
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer userId){
        this.user_service.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("deleted",true), HttpStatus.OK);
    }
}
