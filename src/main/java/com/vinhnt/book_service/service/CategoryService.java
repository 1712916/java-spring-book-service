package com.vinhnt.book_service.service;

import com.vinhnt.book_service.dto.CategoryResponse;
import com.vinhnt.book_service.model.Category;
import com.vinhnt.book_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Thêm một thể loại mới
    public Category addCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    // Lấy danh sách tất cả các thể loại
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(category -> new CategoryResponse(
                                         category.getId(),
                                         category.getName(),
                                         category.getDescription()
                                 ))
                                 .collect(Collectors.toList());
    }

    // Lấy chi tiết một thể loại theo ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }

    // Cập nhật thông tin một thể loại
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existingCategory);
    }

    // Xóa một thể loại
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
