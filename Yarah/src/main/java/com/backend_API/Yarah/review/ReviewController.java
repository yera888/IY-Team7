package com.backend_API.Yarah.review;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    /**
     * Create a review (reviewer is automatically set to current user)
     */
    @PostMapping
    public ResponseEntity<Review> createReview(
            Authentication auth,
            @RequestBody CreateReviewRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile reviewerProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            Review saved = reviewService.createReview(
                reviewerProfile.getProfileId(),
                req.getReviewedProfileId(),
                req.getRating(),
                req.getComment()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all reviews received by a profile
     */
    @GetMapping("/for-profile/{profileId}")
    public ResponseEntity<List<Review>> getReviewsForProfile(@PathVariable Long profileId) {
        try {
            List<Review> reviews = reviewService.getReviewsForProfile(profileId);
            return ResponseEntity.ok(reviews);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all reviews written by a profile
     */
    @GetMapping("/by-profile/{profileId}")
    public ResponseEntity<List<Review>> getReviewsByProfile(@PathVariable Long profileId) {
        try {
            List<Review> reviews = reviewService.getReviewsByProfile(profileId);
            return ResponseEntity.ok(reviews);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all reviews (admin/debug)
     */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAll());
    }

    /**
     * Get a specific review
     */
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getById(id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Delete a review (only if you wrote it)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            Authentication auth,
            @PathVariable Long id) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile reviewerProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            reviewService.deleteReview(id, reviewerProfile.getProfileId());
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Data
    public static class CreateReviewRequest {
        private Long reviewedProfileId;
        private int rating;
        private String comment;
    }
}