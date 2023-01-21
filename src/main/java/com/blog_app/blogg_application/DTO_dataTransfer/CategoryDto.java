package com.blog_app.blogg_application.DTO_dataTransfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer id;


    private String categoryTitle;


    private String categoryDesc;
}
