package com.backend_API.Yarah.listing;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.backend_API.Yarah.seller.*;
import com.backend_API.Yarah.sales.SalesService;

@Service
@RequiredArgsConstructor
@Transactional
public class ListingService {
    private final ListingRepository listingRepository;
    private final SellerRepository sellerRepository;
    private final SalesService saleService;
    
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListingsBySeller(Seller seller) {
        return listingRepository.findBySeller(seller);
    }

    public Listing updateListing(Long listingId, Listing listingInfo) {
        Listing listing = listingRepository.findById(listingId)
            .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        
        // Check if the listing is being marked as sold for the first time
        boolean wasNotSold = !listing.getSold();
        boolean isNowSold = listingInfo.getSold();
        
        listing.setDescription(listingInfo.getDescription());
        listing.setCondition(listingInfo.getCondition());
        listing.setListingPhotoPath(listingInfo.getListingPhotoPath());
        listing.setSize(listingInfo.getSize());
        listing.setWeight(listingInfo.getWeight());
        listing.setPrice(listingInfo.getPrice());
        listing.setAvailable(listingInfo.getAvailable());
        listing.setSold(listingInfo.getSold());

        Listing savedListing = listingRepository.save(listing);
        
        // If the listing is marked as sold for the first time, record the sale
        if (wasNotSold && isNowSold) {
            Seller seller = listing.getSeller();
            
            // Add to seller balance
            seller.addToBalance(listing.getPrice());
            sellerRepository.save(seller);
            
            // Record the sale
            saleService.recordSale(seller, listing, listing.getPrice());
        }
        
        return savedListing;
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
