package com.backend_API.Yarah.review;

import com.backend_API.Yarah.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "reviewer_profile_id", "reviewed_profile_id" })
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    /**
     * Profile writing the review
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewer_profile_id", nullable = false)
    private Profile reviewerProfile;

    /**
     * Profile being reviewed
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewed_profile_id", nullable = false)
    private Profile reviewedProfile;

    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private int rating;

    @NotBlank
    @Column(name = "comment", nullable = false, columnDefinition = "TEXT")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}