package com.backend_API.Yarah.listing;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @Column(nullable = false, columnDefinition = "TEXT")
    private String listingPhotoPath;

    @NotNull
    @Column(nullable = false)
    private BigDecimal size;

    @NotNull
    @Column(nullable = false)
    private BigDecimal weight;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    private boolean available = true;

    @NotNull
    private boolean sold = true;

    public Listing (Long listingId, String description, String condition, String listingPhotoPath, BigDecimal size, BigDecimal weight, BigDecimal price, boolean available, boolean sold) {
        this.listingId = listingId;
        this.description = description;
        this.condition = condition;
        this.listingPhotoPath = listingPhotoPath;
        this.size = size;
        this.weight = weight;
        this.price = price;
        this.available = available;
        this.sold = sold;
    }

    public Listing ( String description, String condition, String listingPhotoPath, BigDecimal size, BigDecimal weight, BigDecimal price, boolean available, boolean sold) {
        this.description = description;
        this.condition = condition;
        this.listingPhotoPath = listingPhotoPath;
        this.size = size;
        this.weight = weight;
        this.price = price;
        this.available = available;
        this.sold = sold;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long id) {
        this.listingId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getListingPhotoPath() {
        return listingPhotoPath;
    }

    public void setListingPhotoPath(String listingPhotoPath) {
        this.listingPhotoPath = listingPhotoPath;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
