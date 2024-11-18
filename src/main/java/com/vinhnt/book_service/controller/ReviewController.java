package com.vinhnt.book_service.controller;

import com.vinhnt.book_service.dto.ApiResponse;
import com.vinhnt.book_service.model.Review;
import com.vinhnt.book_service.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Review>> createReview(@Valid @RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        ApiResponse<Review> response = ApiResponse.success("Review created successfully", createdReview);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByBook(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.getReviewsByBook(bookId);
        ApiResponse<List<Review>> response = ApiResponse.success("Retrieved reviews for book", reviews);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Review>>> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        ApiResponse<List<Review>> response = ApiResponse.success("Retrieved reviews by user", reviews);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        ApiResponse<Void> response = ApiResponse.success("Review deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
