package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.profile.ProfileService;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;
    private final UserRepository userRepository;
    private final ProfileService profileService;

    @Transactional(readOnly = true)
    public SellerProfileForm getFormForUser(Long userId) {
        SellerProfile sp = sellerProfileRepository.findByUser_UserId(userId)
                .orElse(null);

        boolean locationEnabled = profileService.getByUserId(userId).isLocationEnabled();

        if (sp == null) {
            SellerProfileForm f = new SellerProfileForm();
            f.setUserId(userId);
            f.setLocationEnabled(locationEnabled);
            return f;
        }

        return SellerProfileForm.fromEntity(sp, locationEnabled);
    }

    public void saveFromForm(SellerProfileForm form, Long currentUserId) {
        if (!currentUserId.equals(form.getUserId())) {
            throw new IllegalArgumentException("Cannot edit seller profile for a different user");
        }

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        SellerProfile sp = sellerProfileRepository.findByUser_UserId(currentUserId)
                .orElseGet(() -> {
                    SellerProfile fresh = new SellerProfile();
                    fresh.setUser(user);
                    return fresh;
                });

        sp.setShopName(form.getShopName());
        sp.setBio(form.getBio());
        sp.setPayoutDetails(form.getPayoutDetails());

        sellerProfileRepository.save(sp);

        profileService.markUserAsSeller(currentUserId, form.isLocationEnabled());
    }
}
