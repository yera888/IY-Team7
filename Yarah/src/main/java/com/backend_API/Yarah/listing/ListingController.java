package com.backend_API.Yarah.listing;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<Listing> create(@RequestBody @Valid ListingCreateRequest req) {
        try {
            Listing listing = new Listing();
            listing.setTitle(req.getTitle());
            listing.setDescription(req.getDescription());
            listing.setPriceCents(req.getPriceCents());
            listing.setCategory(req.getCategory());
            listing.setCondition(req.getCondition());
            listing.setTags(req.getTags());
            listing.setImageUrl(req.getImageUrl());
            listing.setListingStatus(req.getListingStatus() != null ? req.getListingStatus() : "ACTIVE");

            Listing saved = listingService.createListing(req.getSellerProfileId(), listing);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> update(@PathVariable Long id,
            @RequestBody @Valid ListingUpdateRequest req) {
        try {
            Listing tmp = new Listing();
            tmp.setTitle(req.getTitle());
            tmp.setDescription(req.getDescription());
            tmp.setPriceCents(req.getPriceCents());
            tmp.setCategory(req.getCategory());
            tmp.setCondition(req.getCondition());
            tmp.setTags(req.getTags());
            tmp.setImageUrl(req.getImageUrl());
            tmp.setListingStatus(req.getListingStatus());

            if (req.getSellerProfileId() != null) {
                // we only pass an id here, the service will validate it
                com.backend_API.Yarah.seller.SellerProfile sp = new com.backend_API.Yarah.seller.SellerProfile();
                sp.setSellerProfileId(req.getSellerProfileId());
                tmp.setSellerProfile(sp);
            }

            Listing updated = listingService.updateListing(id, tmp);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAll(@RequestParam(required = false) Boolean activeOnly) {
        if (Boolean.TRUE.equals(activeOnly)) {
            return ResponseEntity.ok(listingService.getAllActive());
        }
        return ResponseEntity.ok(listingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(listingService.getById(id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            listingService.deleteListing(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    // DTOs so your JSON is clean and front-end friendly
    @Data
    public static class ListingCreateRequest {
        private Long sellerProfileId;
        private String title;
        private String description;
        private Long priceCents;
        private String category;
        private String condition;
        private String tags; // JSON string or comma separated, up to you
        private String imageUrl;
        private String listingStatus;
    }

    @Data
    public static class ListingUpdateRequest {
        private Long sellerProfileId;
        private String title;
        private String description;
        private Long priceCents;
        private String category;
        private String condition;
        private String tags;
        private String imageUrl;
        private String listingStatus;
    }
}
