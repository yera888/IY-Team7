package com.backend_API.Yarah.listing;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.backend_API.Yarah.seller.SellerService;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;
    private final SellerService sellerService;
    
    @PostMapping
    public ResponseEntity<Listing> createListing(@Valid @RequestBody Listing listing) {
        return ResponseEntity.ok(listingService.createListing(listing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updatedListing(@PathVariable Long listingId, @Valid @RequestBody Listing listingInfo) {
        return ResponseEntity.ok(listingService.updateListing(listingId, listingInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long listingId) {
        listingService.deleteListing(listingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable Long listingId) {
        return ResponseEntity.ok(listingService.getListingById(listingId));
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAvailableListings() {
        return ResponseEntity.ok(listingService.getAvailableListings());
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Listing>> getListingBySeller(@PathVariable Long sellerId) {
        return ResponseEntity.ok(listingService.getListingBySeller(sellerService.getSellerById(sellerId)));
    }
}
