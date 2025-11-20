package com.backend_API.Yarah.listing;

import com.backend_API.Yarah.seller.SellerProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private Long listingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_profile_id", nullable = false)
    private SellerProfile sellerProfile;

    @NotBlank
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // store money in cents
    @NotNull
    @Column(name = "price_cents", nullable = false)
    private Long priceCents;

    @NotBlank
    @Column(name = "category", nullable = false, length = 100)
    private String category; // "shirt","skirt","dress","shoes",...

    @Column(name = "item_condition", length = 60)
    private String condition; // "NEW","LIKE_NEW","GOOD",...

    // lightweight tags, stored as JSON (e.g. '["vintage","y2k"]')
    @Column(name = "tags", columnDefinition = "jsonb")
    private String tags;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @NotBlank
    @Column(name = "listing_status", nullable = false, length = 30)
    private String listingStatus = "ACTIVE"; // ACTIVE | RESERVED | SOLD | CANCELLED

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // convenience helpers if you want
    public void markSold() {
        this.listingStatus = "SOLD";
    }

    public void markCancelled() {
        this.listingStatus = "CANCELLED";
    }
}
