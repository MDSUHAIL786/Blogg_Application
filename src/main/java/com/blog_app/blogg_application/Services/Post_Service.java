package com.blog_app.blogg_application.Services;

import com.blog_app.blogg_application.DTO_dataTransfer.PostDto;

import java.util.List;

public interface Post_Service {
    //create
    PostDto createPost(PostDto newPostDto, Integer userId, Integer catId);

    //update
    PostDto updatePost(PostDto newPostDto, Integer post_id, Integer user_id, Integer cat_id);

    //delete
    void deletePost(Integer post_id);

    //get by id
    PostDto getPost(Integer post_id);

    //get all
    List<PostDto> getAllPost();

    //get by category
    List<PostDto> getByCategory(Integer cat_id);

    //get by user
    List<PostDto> getByUser(Integer user_id);
}
