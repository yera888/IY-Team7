package com.backend_API.Yarah.seller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {
    private final SellerRepository SellerRepository;

    public Seller createSeller(Seller seller) {
        if (SellerRepository.existsByEmail(seller.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        return SellerRepository.save(seller);
    }

    public Seller getSellerById(Long sellerId) {
        return SellerRepository.findById(sellerId).orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

    public Seller getSellerByEmail(String email) {
        return SellerRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }
}
