package com.vinhnt.book_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookResponse {
    private Long id;
    private String title;
    private LocalDate publishedDate;
    private String isbn;
    private BigDecimal price;
    private String description;
    private List<String> images;
    private List<CategoryResponse> categories; // Danh sách thể loại
    private List<AuthorResponse> authors;     // Danh sách tác giả

    // Constructor
    public BookResponse(Long id, String title, LocalDate publishedDate, String isbn, BigDecimal price,
                        String description, List<String> images, List<CategoryResponse> categories,
                        List<AuthorResponse> authors) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.images = images;
        this.categories = categories;
        this.authors = authors;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    public List<AuthorResponse> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorResponse> authors) {
        this.authors = authors;
    }
}
