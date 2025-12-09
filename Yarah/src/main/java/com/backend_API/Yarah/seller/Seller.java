package com.backend_API.Yarah.seller;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.backend_API.Yarah.listing.Listing;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("seller")
    private List<Listing> listing = new ArrayList<>();

    private String phoneNumber;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Seller(Long id) {
        this.id = id;
    }

    public void addToBalance(BigDecimal amount) {
        if (this.balance == null) {
            this.balance = BigDecimal.ZERO;
        }
        this.balance = this.balance.add(amount);
    }
}