package com.backend_API.Yarah.seller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {
    Optional<SellerProfile> findByUser_UserId(Long userId);
    boolean existsByUser_UserId(Long userId);
}
