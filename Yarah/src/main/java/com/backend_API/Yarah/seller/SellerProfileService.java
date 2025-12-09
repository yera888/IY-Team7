package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    /**
     * Create a new seller profile and upgrade user to SELLER role
     */
    public SellerProfile createSellerProfile(SellerProfileRequest req) {
        if (req.getUserId() == null) {
            throw new IllegalArgumentException("User id required");
        }

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (sellerProfileRepository.existsByUser(user)) {
            throw new IllegalStateException("User already has a seller profile");
        }

        // Create seller profile
        SellerProfile sp = new SellerProfile();
        sp.setUser(user);
        sp.setShopName(req.getShopName());
        sp.setBio(req.getBio());
        sp.setPayoutDetails(req.getPayoutDetails());

        SellerProfile saved = sellerProfileRepository.save(sp);

        // Upgrade profile to SELLER and handle location
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("Profile not found for user"));

        profile.setAccountType("SELLER");
        
        if (req.getLocationEnabled() != null && req.getLocationEnabled()) {
            profile.setLocationEnabled(true);
            if (req.getLocation() != null) {
                profile.setLocation(req.getLocation());
            }
        }

        profileRepository.save(profile);

        return saved;
    }

    /**
     * Update existing seller profile
     */
    public SellerProfile updateSellerProfile(Long sellerProfileId, SellerProfileRequest req) {
        SellerProfile sp = sellerProfileRepository.findById(sellerProfileId)
                .orElseThrow(() -> new IllegalArgumentException("Seller profile not found"));

        if (req.getShopName() != null) sp.setShopName(req.getShopName());
        if (req.getBio() != null) sp.setBio(req.getBio());
        if (req.getPayoutDetails() != null) sp.setPayoutDetails(req.getPayoutDetails());

        SellerProfile saved = sellerProfileRepository.save(sp);

        // Update location in profile if provided
        if (req.getLocationEnabled() != null || req.getLocation() != null) {
            Profile profile = profileRepository.findByUser(sp.getUser())
                    .orElseThrow(() -> new IllegalStateException("Profile not found"));

            if (req.getLocationEnabled() != null) {
                profile.setLocationEnabled(req.getLocationEnabled());
                if (!req.getLocationEnabled()) {
                    profile.setLocation(null);
                }
            }
            if (req.getLocation() != null && profile.isLocationEnabled()) {
                profile.setLocation(req.getLocation());
            }

            profileRepository.save(profile);
        }

        return saved;
    }

    @Transactional(readOnly = true)
    public SellerProfile getByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return sellerProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Seller profile not found"));
    }

    @Transactional(readOnly = true)
    public SellerProfile getById(Long id) {
        return sellerProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seller profile not found"));
    }
}
