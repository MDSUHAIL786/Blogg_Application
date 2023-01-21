package com.blog_app.blogg_application.Services.Implmentation;

import com.blog_app.blogg_application.DTO_dataTransfer.CategoryDto;
import com.blog_app.blogg_application.Entities.Category;
import com.blog_app.blogg_application.Exception.ResourceNotFoundException;
import com.blog_app.blogg_application.Repositories.Category_Repo;
import com.blog_app.blogg_application.Services.Category_service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Category_impl implements Category_service {
    @Autowired
    private Category_Repo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto newCategory) {
        Category cat=this.modelMapper.map(newCategory,Category.class);
        return this.modelMapper.map(this.categoryRepo.save(cat),CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto category, Integer category_id) {
        Category cat=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("category","id",category_id));
        cat.setCategoryDesc(category.getCategoryDesc());
        cat.setCategoryTitle(category.getCategoryTitle());
        this.categoryRepo.save(cat);
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer category_id) {
        Category cat=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("category","id",category_id));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> ans =new ArrayList<>();
        this.categoryRepo.findAll().forEach((cat)->{
            ans.add(this.modelMapper.map(cat,CategoryDto.class));
        });
        return ans;
    }

    @Override
    public void deleteCategory(Integer category_id) {
        Category cat=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("category","id",category_id));
        this.categoryRepo.delete(cat);

    }
}
