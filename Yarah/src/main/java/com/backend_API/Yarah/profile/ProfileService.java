package com.backend_API.Yarah.profile;

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
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public Profile createOrUpdate(Profile profile) {
        if (profile.getProfileId() != null) {
            Profile existing = profileRepository.findById(profile.getProfileId())
                    .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

            existing.setFirstName(profile.getFirstName());
            existing.setLastName(profile.getLastName());
            existing.setAccountType(profile.getAccountType());
            existing.setLocationEnabled(profile.isLocationEnabled());
            existing.setLocation(profile.getLocation());

            return profileRepository.save(existing);
        } else {
            if (profile.getUser() == null || profile.getUser().getUserId() == null) {
                throw new IllegalArgumentException("User id must be provided.");
            }

            User user = userRepository.findById(profile.getUser().getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + profile.getUser().getUserId()));

            profile.setUser(user);
            if (profile.getAccountType() == null) {
                profile.setAccountType("CUSTOMER");
            }
            return profileRepository.save(profile);
        }
    }

    @Transactional(readOnly = true)
    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
    }

    @Transactional(readOnly = true)
    public Profile getByUserId(Long userId) {
        return profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user"));
    }

    @Transactional(readOnly = true)
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public void delete(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException("Profile not found");
        }
        profileRepository.deleteById(id);
    }

    /**
     * Update location settings for a profile
     */
    public Profile updateLocation(Long profileId, boolean enabled, String location) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        profile.setLocationEnabled(enabled);
        if (enabled && location != null) {
            profile.setLocation(location);
        } else if (!enabled) {
            profile.setLocation(null);
        }

        return profileRepository.save(profile);
    }

    /**
     * Upgrade account from CUSTOMER to SELLER
     */
    public Profile upgradeToSeller(Long userId) {
        Profile profile = profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user"));

        if ("SELLER".equals(profile.getAccountType())) {
            throw new IllegalStateException("User is already a seller");
        }

        profile.setAccountType("SELLER");
        return profileRepository.save(profile);
    }

    /**
     * Update profile by current user
     */
    public Profile updateProfile(Long profileId, String firstName, String lastName, 
                                  Boolean locationEnabled, String location) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        if (firstName != null) profile.setFirstName(firstName);
        if (lastName != null) profile.setLastName(lastName);
        if (locationEnabled != null) {
            profile.setLocationEnabled(locationEnabled);
            if (!locationEnabled) {
                profile.setLocation(null);
            }
        }
        if (location != null && profile.isLocationEnabled()) {
            profile.setLocation(location);
        }

        return profileRepository.save(profile);
    }
}