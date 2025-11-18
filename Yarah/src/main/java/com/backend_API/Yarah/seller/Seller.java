package com.backend_API.Yarah.seller;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("seller")
    private Listing listing;

    private String phoneNumber;

    public Seller(Long id) {
        this.id = id;
    }

}