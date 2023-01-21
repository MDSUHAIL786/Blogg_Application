package com.blog_app.blogg_application.DTO_dataTransfer;

import com.blog_app.blogg_application.Entities.Post;
import com.blog_app.blogg_application.Entities.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    @NotEmpty
    private Integer comment_Id;

    @NotEmpty
    private String comment;

    private PostDto p;
}
