package com.backend_API.Yarah.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByShippingAddressContaining(String shippingAddress);
    List<User> findByPhoneNumberContaining(String phoneNumber);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}