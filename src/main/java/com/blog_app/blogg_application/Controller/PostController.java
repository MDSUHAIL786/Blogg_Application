package com.blog_app.blogg_application.Controller;

import com.blog_app.blogg_application.DTO_dataTransfer.ApiResponse;
import com.blog_app.blogg_application.DTO_dataTransfer.PostDto;
import com.blog_app.blogg_application.Services.Post_Service;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private Post_Service post_service;

    @PostMapping("/user/{user_id}/category/{cat_id}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto newPost, @PathVariable Integer user_id, @PathVariable Integer cat_id){
        return new ResponseEntity<>(this.post_service.createPost(newPost,user_id,cat_id), HttpStatus.CREATED);
    }

    @PutMapping("/post/{post_id}/user/{user_id}/category/{cat_id}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto post, @PathVariable("post_id") Integer id, @PathVariable("user_id") Integer user_id, @PathVariable("cat_id") Integer cat_id){
        this.post_service.updatePost(post,id,user_id,cat_id);
        return ResponseEntity.ok(new ApiResponse(String.format("Post with Id:%d is updated successfully",id),true));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
        this.post_service.deletePost(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully.",true));
    }

    @GetMapping("/posts/")
    public ResponseEntity<List<PostDto>> getAllPost(){
        return new ResponseEntity<>(this.post_service.getAllPost(),HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
        return new ResponseEntity<>(this.post_service.getPost(id),HttpStatus.OK);
    }

    @GetMapping("/category/{cat_id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCat(@PathVariable Integer cat_id){
        return new ResponseEntity<List<PostDto>>(this.post_service.getByCategory(cat_id),HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer user_id){
        return new ResponseEntity<List<PostDto>>(this.post_service.getByUser(user_id),HttpStatus.OK);
    }

    @GetMapping("/mocked-api")
    public ResponseEntity<Map<String, Object>> getRadarData() throws IOException {
        ClassPathResource staticDataResource = new ClassPathResource("26590f1e-7238-11ed-9827-4e51a66a11b2.json");
        String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

        return new ResponseEntity<>(new JSONObject(staticDataString).toMap(), HttpStatus.OK);
    }
}
