package com.vinhnt.book_service.controller;

import com.vinhnt.book_service.dto.ApiResponse;
import com.vinhnt.book_service.dto.BookRequest;
import com.vinhnt.book_service.dto.BookResponse;
import com.vinhnt.book_service.model.Book;
import com.vinhnt.book_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponse>>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(ApiResponse.success("Books fetched successfully", books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        ApiResponse<Book> response = ApiResponse.success(String.format("Retrieved book with ID %d successfully", id), book);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody BookRequest bookRequest) {
        Book createdBook = bookService.createBook(bookRequest);
        return ResponseEntity.ok(ApiResponse.success("Book created successfully", createdBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        ApiResponse<Book> response = ApiResponse.success(String.format("Updated book with ID %d successfully", id), updatedBook);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        ApiResponse<Void> response = ApiResponse.success(String.format("Deleted book with ID %d successfully", id), null);
        return ResponseEntity.ok(response);
    }
}