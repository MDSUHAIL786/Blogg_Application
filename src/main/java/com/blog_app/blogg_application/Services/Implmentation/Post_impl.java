package com.blog_app.blogg_application.Services.Implmentation;

import com.blog_app.blogg_application.DTO_dataTransfer.PostDto;
import com.blog_app.blogg_application.Entities.Category;
import com.blog_app.blogg_application.Entities.Post;
import com.blog_app.blogg_application.Entities.user;
import com.blog_app.blogg_application.Exception.ResourceNotFoundException;
import com.blog_app.blogg_application.Repositories.Category_Repo;
import com.blog_app.blogg_application.Repositories.Post_Repo;
import com.blog_app.blogg_application.Repositories.User_Repo;
import com.blog_app.blogg_application.Services.Post_Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Post_impl implements Post_Service {
    @Autowired
    private Post_Repo post_repo;

    @Autowired
    private User_Repo userRepo;

    @Autowired
    private Category_Repo categoryRepo;

    @Autowired
    private ModelMapper mm;

    @Override
    public PostDto createPost(PostDto newPostDto, Integer userId, Integer catId) {
        Category cat=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","id",catId));
        user user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Post newPost=this.mm.map(newPostDto,Post.class);
        newPost.setCat(cat);
        newPost.setImg("default.png");
        newPost.setUser(user);
        newPost.setAddedDate(new Date());
        return this.mm.map(this.post_repo.save(newPost), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto newPostDto, Integer post_id, Integer user_id, Integer cat_id) {
        Post exist_post=this.post_repo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post","id",post_id));
        Category cat=this.categoryRepo.findById(cat_id).orElseThrow(()->new ResourceNotFoundException("Category","id",cat_id));
        user user=this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("user","id",user_id));
        exist_post.setContent(newPostDto.getContent());
        exist_post.setTitle(newPostDto.getTitle());
        exist_post.setCat(cat);
        exist_post.setUser(user);
        Post updated =this.post_repo.save(exist_post);
        return this.mm.map(updated, PostDto.class);
    }

    @Override
    public void deletePost(Integer post_id) {
        Post exist_post=this.post_repo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post","id",post_id));
        this.post_repo.delete(exist_post);
    }

    @Override
    public PostDto getPost(Integer post_id) {
        Post exist_post=this.post_repo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post","id",post_id));
        return this.mm.map(exist_post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<PostDto> ans=new ArrayList<>();
        this.post_repo.findAll().forEach((post)->{
            ans.add(this.mm.map(post, PostDto.class));
        });
        return ans;
    }

    @Override
    public List<PostDto> getByCategory(Integer cat_id) {
        Category cat=this.categoryRepo.findById(cat_id).orElseThrow(()->new ResourceNotFoundException("Category","id",cat_id));
        List<Post> catAllPosts=this.post_repo.findByCat(cat);
        List<PostDto> ansList=catAllPosts.stream().map((post)->this.mm.map(post, PostDto.class)).collect(Collectors.toList());
        return ansList;
    }

    @Override
    public List<PostDto> getByUser(Integer user_id) {
        user user=this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User","id",user_id));
        List<Post> catAllPosts=this.post_repo.findByUser(user);
        List<PostDto> ansList=catAllPosts.stream().map((post)->this.mm.map(post, PostDto.class)).collect(Collectors.toList());
        return ansList;
    }
}
