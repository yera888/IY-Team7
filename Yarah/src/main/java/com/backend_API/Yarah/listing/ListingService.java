package com.backend_API.Yarah.listing;

import com.backend_API.Yarah.seller.SellerProfile;
import com.backend_API.Yarah.seller.SellerProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ListingService {

    private final ListingRepository listingRepository;
    private final SellerProfileRepository sellerProfileRepository;

    public Listing createListing(Long sellerProfileId, @Valid Listing listing) {
        if (sellerProfileId == null) {
            throw new IllegalArgumentException("Seller profile id is required");
        }

        SellerProfile sellerProfile = sellerProfileRepository.findById(sellerProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Seller profile not found"));

        listing.setListingId(null); // ensure new
        listing.setSellerProfile(sellerProfile);

        if (listing.getListingStatus() == null || listing.getListingStatus().isBlank()) {
            listing.setListingStatus("ACTIVE");
        }

        return listingRepository.save(listing);
    }

    public Listing updateListing(Long listingId, @Valid Listing updated) {
        Listing existing = listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setPriceCents(updated.getPriceCents());
        existing.setCategory(updated.getCategory());
        existing.setCondition(updated.getCondition());
        existing.setTags(updated.getTags());
        existing.setImageUrl(updated.getImageUrl());
        if (updated.getListingStatus() != null && !updated.getListingStatus().isBlank()) {
            existing.setListingStatus(updated.getListingStatus());
        }

        // if you want to allow moving a listing to another seller profile:
        if (updated.getSellerProfile() != null &&
                updated.getSellerProfile().getSellerProfileId() != null) {

            Long spId = updated.getSellerProfile().getSellerProfileId();
            SellerProfile sp = sellerProfileRepository.findById(spId)
                    .orElseThrow(() -> new EntityNotFoundException("Seller profile not found"));
            existing.setSellerProfile(sp);
        }

        return listingRepository.save(existing);
    }

    @Transactional(readOnly = true)
    public Listing getById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
    }

    @Transactional(readOnly = true)
    public List<Listing> getAllActive() {
        return listingRepository.findByListingStatus("ACTIVE");
    }

    @Transactional(readOnly = true)
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    public void deleteListing(Long id) {
        if (!listingRepository.existsById(id)) {
            throw new EntityNotFoundException("Listing not found");
        }
        listingRepository.deleteById(id);
    }
}
