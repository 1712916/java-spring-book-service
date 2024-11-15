package com.vinhnt.book_service.repository;

import com.vinhnt.book_service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Tìm tác giả theo tên (nếu cần)
    Author findByName(String name);

    // Kiểm tra xem một Author có tồn tại dựa trên tên
    boolean existsByName(String name);
}
