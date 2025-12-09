package com.backend_API.Yarah.seller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {
    private final SellerRepository sellerRepository;

    public Seller createSeller(Seller seller) {
        if (sellerRepository.existsByEmail(seller.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller sellerDetails) {
        @SuppressWarnings("null")
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        seller.setName(sellerDetails.getName());
        if (!seller.getEmail().equals(sellerDetails.getEmail()) &&
                sellerRepository.existsByEmail(sellerDetails.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        seller.setEmail(sellerDetails.getEmail());
        seller.setPhoneNumber(sellerDetails.getPhoneNumber());

        return sellerRepository.save(seller);
    }

    @SuppressWarnings("null")
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

    public Seller getSellerByEmail(String email) {
        return sellerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

    public Seller authenticate(String email, String password) {
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!seller.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return seller;
    }
}
