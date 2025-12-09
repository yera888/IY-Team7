package com.backend_API.Yarah.review;

import com.backend_API.Yarah.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    /**
     * Find all reviews received by a profile (reviews where this profile is being reviewed)
     */
    List<Review> findByReviewedProfile(Profile reviewedProfile);

    /**
     * Find all reviews written by a profile (reviews where this profile is the reviewer)
     */
    List<Review> findByReviewerProfile(Profile reviewerProfile);

    /**
     * Check if a specific reviewer-reviewed pair already exists
     */
    Optional<Review> findByReviewerProfileAndReviewedProfile(
        Profile reviewerProfile, 
        Profile reviewedProfile
    );

    /**
     * Check if a review exists
     */
    boolean existsByReviewerProfileAndReviewedProfile(
        Profile reviewerProfile,
        Profile reviewedProfile
    );
}