package com.backend_API.Yarah.customerseller;

import com.csc340.backend_API.Yarah.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customers")
public class CustomerSellerProfile {
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

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<User> user= new ArrayList<>();

    @NotBlank
    private String shippingAddress;

    private String phoneNumber;

    public CustomerSellerProfile(Long id) {
        this.id = id;
    }

    
}
