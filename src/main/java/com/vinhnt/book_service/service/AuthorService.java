package com.vinhnt.book_service.service;

import com.vinhnt.book_service.model.Author;
import com.vinhnt.book_service.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Lấy danh sách tất cả Author
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Lấy thông tin một Author theo ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                               .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));
    }

    // Thêm mới Author
    public Author addAuthor(Author author) {
        if (authorRepository.existsByName(author.getName())) {
            throw new IllegalArgumentException("Author already exists with name: " + author.getName());
        }
        return authorRepository.save(author);
    }

    // Cập nhật Author
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBio(updatedAuthor.getBio());
        return authorRepository.save(existingAuthor);
    }

    // Xóa Author theo ID
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new IllegalArgumentException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
