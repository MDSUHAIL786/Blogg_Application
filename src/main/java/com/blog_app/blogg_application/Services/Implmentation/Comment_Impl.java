package com.blog_app.blogg_application.Services.Implmentation;

import com.blog_app.blogg_application.DTO_dataTransfer.CommentDto;
import com.blog_app.blogg_application.DTO_dataTransfer.PostDto;
import com.blog_app.blogg_application.Entities.Comment;
import com.blog_app.blogg_application.Entities.Post;
import com.blog_app.blogg_application.Entities.user;
import com.blog_app.blogg_application.Exception.GlobalExceptionHandler;
import com.blog_app.blogg_application.Exception.ResourceNotFoundException;
import com.blog_app.blogg_application.Repositories.Comment_Repo;
import com.blog_app.blogg_application.Repositories.Post_Repo;
import com.blog_app.blogg_application.Repositories.User_Repo;
import com.blog_app.blogg_application.Services.Comment_Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class Comment_Impl implements Comment_Service {
    @Autowired
    ModelMapper mm;

    @Autowired
    Comment_Repo comment_repo;

    @Autowired
    User_Repo userRepo;

    @Autowired
    Post_Repo postRepo;

    @Override
    public CommentDto createComment(CommentDto comment,Integer post_id) {
        Comment c=this.mm.map(comment, Comment.class);
        Post post=this.postRepo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post","id",post_id));
        c.setP(post);
        return this.mm.map(this.comment_repo.save(c),CommentDto.class);



    }

//    @Override
//    public CommentDto editComment(CommentDto comment, Integer comment_id) {
//        Comment existComment=this.comment_repo.findById(comment_id).orElseThrow(()->new ResourceNotFoundException("Comment","id",comment_id));
//        existComment.setComment(comment.getComment());
//
//        return null;
//    }

    @Override
    public CommentDto getCommentById(Integer comment_id) {
        Comment comment=this.comment_repo.findById(comment_id).orElseThrow(()->new ResourceNotFoundException("Comment","id",comment_id));
        return this.mm.map(comment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<CommentDto> allComments=this.comment_repo.findAll().stream().map(comment->this.mm.map(comment,CommentDto.class)).collect(Collectors.toList());
        return allComments;
    }

    @Override
    public void deleteComment(Integer comment_id) {
        Comment comment=this.comment_repo.findById(comment_id).orElseThrow(()->new ResourceNotFoundException("Comment","id",comment_id));
        this.comment_repo.deleteById(comment_id);
    }


    @Override
    public List<CommentDto> getByP(Integer post_id) {
        Post p=this.postRepo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("User","id",post_id));
        List<Comment> allCommentsPost=this.comment_repo.findByP(p);
        List<CommentDto> ansList=allCommentsPost.stream().map((comment)->this.mm.map(comment, CommentDto.class)).collect(Collectors.toList());
        return ansList;
    }
}
