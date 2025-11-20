package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.user.User; // ✅ ADD
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {

    Optional<SellerProfile> findByUser(User user);

    boolean existsByUser(User user);
}
