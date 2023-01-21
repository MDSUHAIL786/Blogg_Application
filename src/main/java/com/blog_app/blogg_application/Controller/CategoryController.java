package com.blog_app.blogg_application.Controller;

import com.blog_app.blogg_application.DTO_dataTransfer.ApiResponse;
import com.blog_app.blogg_application.DTO_dataTransfer.CategoryDto;
import com.blog_app.blogg_application.Entities.Category;
import com.blog_app.blogg_application.Services.Category_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private Category_service cat_service;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto newCat){
        return new ResponseEntity<>(this.cat_service.createCategory(newCat), HttpStatus.CREATED);
    }

    @PutMapping("/{CategoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto cat,@PathVariable("CategoryId") Integer id){
        return ResponseEntity.ok(this.cat_service.updateCategory(cat,id));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> allcategories(){
        return ResponseEntity.ok(this.cat_service.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> allcategories(@PathVariable Integer id){
        return ResponseEntity.ok(this.cat_service.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        this.cat_service.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully",true));
    }

}
