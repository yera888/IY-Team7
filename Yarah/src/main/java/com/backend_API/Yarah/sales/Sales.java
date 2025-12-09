package com.backend_API.Yarah.sales;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backend_API.Yarah.seller.Seller;
import com.backend_API.Yarah.listing.Listing;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "listingId", nullable = false)
    private Listing listing;

    @Column(nullable = false)
    private BigDecimal saleAmount;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    public Sales(Seller seller, Listing listing, BigDecimal saleAmount) {
        this.seller = seller;
        this.listing = listing;
        this.saleAmount = saleAmount;
        this.saleDate = LocalDateTime.now();
    }
}