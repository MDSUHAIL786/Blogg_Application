package com.blog_app.blogg_application.Services;


import com.blog_app.blogg_application.DTO_dataTransfer.CommentDto;


import java.util.List;

public interface Comment_Service {
    CommentDto createComment(CommentDto comment, Integer post_id);
//    CommentDto editComment(CommentDto comment, Integer comment_id);

    //get
    CommentDto getCommentById(Integer comment_id);

    //get-all
    List<CommentDto> getAllComment();

    //delete
    void deleteComment(Integer comment_id);

    //get by post
    List<CommentDto> getByP(Integer post_id);

}
