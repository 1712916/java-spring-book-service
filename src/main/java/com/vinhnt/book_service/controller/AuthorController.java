package com.vinhnt.book_service.controller;

import com.vinhnt.book_service.dto.ApiResponse;
import com.vinhnt.book_service.model.Author;
import com.vinhnt.book_service.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Lấy danh sách tất cả Author
    @GetMapping
    public ResponseEntity<ApiResponse<List<Author>>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(ApiResponse.success("Authors fetched successfully", authors));
    }

    // Lấy thông tin một Author theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(ApiResponse.success("Author fetched successfully", author));
    }

    // Thêm mới Author
    @PostMapping
    public ResponseEntity<ApiResponse<Author>> addAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.addAuthor(author);
        return ResponseEntity.ok(ApiResponse.success("Author created successfully", createdAuthor));
    }

    // Cập nhật Author
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author author = authorService.updateAuthor(id, updatedAuthor);
        return ResponseEntity.ok(ApiResponse.success("Author updated successfully", author));
    }

    // Xóa Author theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(ApiResponse.success("Author deleted successfully", null));
    }
}
