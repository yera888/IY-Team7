package com.backend_API.Yarah.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/comment")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") long review_id) {
        reviewService.deleteReview(review_id);
        return ResponseEntity.ok().build();

    }




    
}
