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

    public Seller updateSeller(Long sellerId, Seller sellerDetails) {
        Seller seller = SellerRepository.findById(sellerId).orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        seller.setName(sellerDetails.getName());
        if (!seller.getEmail().equals(sellerDetails.getEmail()) && 
            SellerRepository.existsByEmail(sellerDetails.getEmail())) {
                throw new IllegalStateException("Email already registered");
        }
        seller.setEmail(sellerDetails.getEmail());
        seller.setPhoneNumber(sellerDetails.getPhoneNumber());

        return sellerRepository.save(seller);
    }

    public Seller getSellerById(Long sellerId) {
        return SellerRepository.findById(sellerId).orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }

    public Seller getSellerbyEmail(String email) {
        return SellerRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Seller not found"));
    }
}
