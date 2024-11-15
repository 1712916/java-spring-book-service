package com.vinhnt.book_service.repository;

import com.vinhnt.book_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name); // Kiểm tra xem thể loại đã tồn tại chưa
}
