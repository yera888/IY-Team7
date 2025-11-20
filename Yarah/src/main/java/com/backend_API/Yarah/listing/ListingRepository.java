package com.backend_API.Yarah.listing;

import com.backend_API.Yarah.seller.SellerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByListingStatus(String listingStatus);

    List<Listing> findBySellerProfile(SellerProfile sellerProfile);
}
