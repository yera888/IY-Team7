package com.backend_API.Yarah.listing;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.backend_API.Yarah.seller.Seller;

@Data
@NoArgsConstructor
@Entity
@Table(name = "listings")
public class Listing {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listingId;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String condition;

    @NotBlank
    @Column(nullable = false)
    private String imgUrl;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private BigDecimal size;

    @NotBlank
    @Column(nullable = false)
    private BigDecimal weight;

    @NotBlank
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    private boolean available = true;

    @NotNull
    private boolean sold = true;

    @NotBlank
    @Column(nullable = false)
}
