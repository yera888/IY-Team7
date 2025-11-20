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

    public SellerProfile createSellerProfile(SellerProfileRequest req) {
        if (req.getUserId() == null) {
            throw new IllegalArgumentException("User id required");
        }

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (sellerProfileRepository.existsByUser(user)) {
            throw new IllegalStateException("User already has a seller profile");
        }

        SellerProfile sp = new SellerProfile();
        sp.setUser(user);
        sp.setShopName(req.getShopName());
        sp.setBio(req.getBio());
        sp.setPayoutDetails(req.getPayoutDetails());

        SellerProfile saved = sellerProfileRepository.save(sp);

        // Flip profile account type to SELLER
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("Profile not found for user"));

        profile.setAccountType("SELLER");
        profileRepository.save(profile);

        return saved;
    }

    public SellerProfile getByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return sellerProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Seller profile not found"));
    }
}
