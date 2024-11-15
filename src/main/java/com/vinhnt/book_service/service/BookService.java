package com.vinhnt.book_service.service;

import com.vinhnt.book_service.dto.BookRequest;
import com.vinhnt.book_service.exception.BookNotFoundException;
import com.vinhnt.book_service.model.Book;
import com.vinhnt.book_service.model.Category;
import com.vinhnt.book_service.repository.BookRepository;
import com.vinhnt.book_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service

public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                             .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    public Book createBook(BookRequest bookRequest) {
        List<Category> categories = categoryRepository.findAllById(bookRequest.getCategoryIds());
        if (categories.isEmpty()) {
            throw new IllegalArgumentException("Invalid category IDs provided");
        }

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setIsbn(bookRequest.getIsbn());
        book.setPrice(bookRequest.getPrice());
        book.setDescription(bookRequest.getDescription());
        book.setImages(bookRequest.getImages());
        book.setCategories(new HashSet<>(categories));

        return bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id); // Throws exception if book is not found

        // Update fields
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}
