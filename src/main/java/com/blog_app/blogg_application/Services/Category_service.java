package com.blog_app.blogg_application.Services;

import com.blog_app.blogg_application.DTO_dataTransfer.CategoryDto;
import com.blog_app.blogg_application.DTO_dataTransfer.userDto;

import java.util.List;

public interface Category_service {
    //create
    CategoryDto createCategory(CategoryDto newCategory);

    //update
    CategoryDto updateCategory(CategoryDto category, Integer category_id);

    //get
    CategoryDto getCategoryById(Integer category_id);

    //get-all
    List<CategoryDto> getAllCategory();

    //delete
    void deleteCategory(Integer category_id);
}
