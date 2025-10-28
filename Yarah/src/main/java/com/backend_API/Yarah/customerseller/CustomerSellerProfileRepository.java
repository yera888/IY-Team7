package com.backend_API.Yarah.customerseller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerSellerProfileRepository extends JpaRepository<CustomerSellerProfile, Long> {
    List<CustomerSellerProfile> findByShippingAddressContaining(String address);
    List<CustomerSellerProfile> findByPhoneNumberContaining(String phoneNumber);
    boolean existsByEmail(String email);
    Optional<CustomerSellerProfile> findByEmail(String email);
}