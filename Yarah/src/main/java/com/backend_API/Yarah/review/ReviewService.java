package com.backend_API.Yarah.review;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProfileRepository profileRepository;

    /**
     * Create a review from one profile to another
     */
    public Review createReview(Long reviewerProfileId, Long reviewedProfileId, 
                               int rating, String comment) {
        
        if (reviewerProfileId.equals(reviewedProfileId)) {
            throw new IllegalArgumentException("Cannot review yourself");
        }

        Profile reviewer = profileRepository.findById(reviewerProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Reviewer profile not found"));

        Profile reviewed = profileRepository.findById(reviewedProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Reviewed profile not found"));

        // Check if review already exists
        if (reviewRepository.existsByReviewerProfileAndReviewedProfile(reviewer, reviewed)) {
            throw new IllegalArgumentException("You have already reviewed this profile");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }

        Review review = new Review();
        review.setReviewerProfile(reviewer);
        review.setReviewedProfile(reviewed);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    /**
     * Get all reviews received by a profile
     */
    @Transactional(readOnly = true)
    public List<Review> getReviewsForProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return reviewRepository.findByReviewedProfile(profile);
    }

    /**
     * Get all reviews written by a profile
     */
    @Transactional(readOnly = true)
    public List<Review> getReviewsByProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return reviewRepository.findByReviewerProfile(profile);
    }

    @Transactional(readOnly = true)
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    /**
     * Delete a review (only if you wrote it)
     */
    public void deleteReview(Long reviewId, Long reviewerProfileId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        if (!review.getReviewerProfile().getProfileId().equals(reviewerProfileId)) {
            throw new IllegalArgumentException("You can only delete your own reviews");
        }

        reviewRepository.delete(review);
    }
}