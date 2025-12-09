package com.backend_API.Yarah.listing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_API.Yarah.seller.Seller;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findBySellerAndAvailable(Seller seller, boolean available);

    List<Listing> findByAvailable(boolean available);

    List<Listing> findBySellerAndSold(Seller seller, boolean sold);
    
    List<Listing> findBySeller(Seller seller);
}
