package com.blog_app.blogg_application.Controller;

import com.blog_app.blogg_application.DTO_dataTransfer.ApiResponse;
import com.blog_app.blogg_application.DTO_dataTransfer.CommentDto;
import com.blog_app.blogg_application.Services.Comment_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private Comment_Service comment_service;

//    @PostMapping("/user/{user_id}/post/{post_id}/comments")
//    public ResponseEntity<CommentDto> Create_Comment(@RequestBody CommentDto newComment,@PathVariable Integer post_id){
//        return new ResponseEntity<CommentDto>(this.comment_service.createComment(newComment,post_id), HttpStatus.CREATED);
//    }

    @PostMapping("/post/{post_id}/comments")
    public ResponseEntity<CommentDto> Create_Comment(@RequestBody CommentDto newComment,@PathVariable Integer post_id){
        return new ResponseEntity<CommentDto>(this.comment_service.createComment(newComment,post_id), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentDto>> getComment(){
        return ResponseEntity.ok(this.comment_service.getAllComment());
    }
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer id){
        return ResponseEntity.ok(this.comment_service.getCommentById(id));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        this.comment_service.deleteComment(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully",true));
    }

    @GetMapping("/commnets/{post_id}")
    public ResponseEntity<List<CommentDto>> CommentOfPost(@PathVariable Integer post_id){
        return ResponseEntity.ok(this.comment_service.getByP(post_id));
    }
}
