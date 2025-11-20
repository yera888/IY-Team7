package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    /**
     * Create a new seller profile or update an existing one.
     *
     * For CREATE:
     * - sellerProfile.sellerProfileId must be null
     * - sellerProfile.user.userId must be set
     *
     * For UPDATE:
     * - sellerProfile.sellerProfileId must be set (path variable in controller)
     */
    public SellerProfile createOrUpdate(SellerProfile sellerProfile) {
        if (sellerProfile.getSellerProfileId() != null) {
            // UPDATE existing seller profile
            SellerProfile existing = sellerProfileRepository.findById(sellerProfile.getSellerProfileId())
                    .orElseThrow(() -> new EntityNotFoundException("Seller profile not found"));

            if (sellerProfile.getShopName() != null) {
                existing.setShopName(sellerProfile.getShopName());
            }
            if (sellerProfile.getBio() != null) {
                existing.setBio(sellerProfile.getBio());
            }
            if (sellerProfile.getPayoutDetails() != null) {
                existing.setPayoutDetails(sellerProfile.getPayoutDetails());
            }

            return sellerProfileRepository.save(existing);
        } else {
            // CREATE new seller profile
            if (sellerProfile.getUser() == null || sellerProfile.getUser().getUserId() == null) {
                throw new IllegalArgumentException("User id must be provided in sellerProfile.user.userId");
            }

            Long userId = sellerProfile.getUser().getUserId();

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

            // Enforce one seller profile per user
            sellerProfileRepository.findByUser_UserId(userId).ifPresent(sp -> {
                throw new IllegalArgumentException("User already has a seller profile");
            });

            sellerProfile.setUser(user);

            SellerProfile saved = sellerProfileRepository.save(sellerProfile);

            // Flip the profile's accountType to SELLER if a profile exists
            profileRepository.findByUser_UserId(userId).ifPresent(profile -> {
                if (!"SELLER".equalsIgnoreCase(profile.getAccountType())) {
                    profile.setAccountType("SELLER");
                    profileRepository.save(profile);
                }
            });

            return saved;
        }
    }

    @Transactional(readOnly = true)
    public SellerProfile getById(Long id) {
        return sellerProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller profile not found"));
    }

    @Transactional(readOnly = true)
    public SellerProfile getByUserId(Long userId) {
        return sellerProfileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Seller profile not found for user " + userId));
    }

    @Transactional(readOnly = true)
    public List<SellerProfile> getAll() {
        return sellerProfileRepository.findAll();
    }

    public void delete(Long id) {
        if (!sellerProfileRepository.existsById(id)) {
            throw new EntityNotFoundException("Seller profile not found");
        }
        sellerProfileRepository.deleteById(id);
    }
}
