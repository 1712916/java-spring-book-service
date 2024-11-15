package com.vinhnt.book_service.controller;

import com.vinhnt.book_service.dto.ApiResponse;
import com.vinhnt.book_service.dto.CategoryResponse;
import com.vinhnt.book_service.model.Category;
import com.vinhnt.book_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Thêm một thể loại mới
    @PostMapping
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(ApiResponse.success("Category created successfully", createdCategory));
    }

    // Lấy danh sách tất cả thể loại
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success("Categories fetched successfully", categories));
    }

    // Lấy thông tin chi tiết một thể loại theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success("Category fetched successfully", category));
    }

    // Cập nhật thông tin thể loại
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Long id,
            @RequestBody Category updatedCategory) {
        Category updated = categoryService.updateCategory(id, updatedCategory);
        return ResponseEntity.ok(ApiResponse.success("Category updated successfully", updated));
    }

    // Xóa thể loại theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully", null));
    }
}
