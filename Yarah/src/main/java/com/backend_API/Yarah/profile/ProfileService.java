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

            return profileRepository.save(existing);
        } else {
            if (profile.getUser() == null || profile.getUser().getUserId() == null) {
                throw new IllegalArgumentException("User id must be provided in request body (profile.user.userId).");
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

    public void markUserAsSeller(Long userId, boolean locationEnabled) {
        Profile profile = profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user"));

        profile.setAccountType("SELLER");
        profile.setLocationEnabled(locationEnabled);
        profileRepository.save(profile);
    }
}
