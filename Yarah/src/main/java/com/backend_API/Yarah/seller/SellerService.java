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
}
