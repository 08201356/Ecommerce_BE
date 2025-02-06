package base.ecommerce.controller;

import base.ecommerce.model.Category;
import base.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully!!!!", HttpStatus.CREATED);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId) {
        Category savedCategory = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>("Updated Category with ID: " + categoryId, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory (@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
