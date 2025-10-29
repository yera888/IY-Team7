package com.backend_API.Yarah.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long review_id;
    
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String username;
    
    @NotBlank
    @Column(nullable = false)
    private BigDecimal seller_rating;



}
