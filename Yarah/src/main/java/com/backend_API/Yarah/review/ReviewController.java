package com.backend_API.Yarah.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review saved = reviewService.createReview(review);
        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<Review> setSellerRating(@PathVariable("id") Long id,
            @RequestParam BigDecimal rating) {
        Review updated = reviewService.setSellerRating(id, rating);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") long review_id) {
        reviewService.deleteReview(review_id);
        return ResponseEntity.ok().build();
    }
}
