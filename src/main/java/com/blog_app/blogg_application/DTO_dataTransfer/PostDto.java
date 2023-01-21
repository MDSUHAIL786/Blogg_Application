package com.blog_app.blogg_application.DTO_dataTransfer;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Data
public class PostDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String img;

    private Date addedDate;

    private CategoryDto cat;

    private userDto user;

}
