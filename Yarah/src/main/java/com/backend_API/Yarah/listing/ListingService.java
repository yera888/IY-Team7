package com.backend_API.Yarah.listing;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.backend_API.Yarah.seller.*;
@Service
@RequiredArgsConstructor
@Transactional
public class ListingService {
    private final ListingRepository listingRepository;
    
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing updateListing(Long listingId, Listing listingInfo) {
        Listing listing = listingRepository.findById(listingId)
            .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        
        listing.setDescription(listingInfo.getDescription());
        listing.setCondition(listingInfo.getCondition());
        listing.setListingPhotoPath(listingInfo.getListingPhotoPath());
        listing.setSize(listingInfo.getSize());
        listing.setWeight(listingInfo.getWeight());
        listing.setPrice(listingInfo.getPrice());
        listing.setAvailable(listingInfo.getAvailable());

        return listingRepository.save(listing);
    }

    public void deleteListing(Long listingId) {
        if (!listingRepository.existsById(listingId)) {
            throw new EntityNotFoundException("Listing not found");
        }
        listingRepository.deleteById(listingId);
    }

    public Listing getListingById(Long listingId) {
        return listingRepository.findById(listingId)
            .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public List<Listing> getAvailableListings() {
        return listingRepository.findByAvailable(true);
    }

    public List<Listing> getListingBySeller(Seller seller) {
        return listingRepository.findBySellerAndAvailable(seller, true);
    }
}
