package com.vinhnt.book_service.dto;

public class AuthorResponse {
    private Long id;
    private String name;
    private String bio;
    private String description;

    public AuthorResponse(Long id, String name, String bio, String description) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
