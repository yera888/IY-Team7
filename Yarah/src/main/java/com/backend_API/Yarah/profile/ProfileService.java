package com.backend_API.Yarah.profile;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;


    public Profile createOrUpdate(Profile profile) {
        if (profile.getId() != null) {
            Profile existing = profileRepository.findById(profile.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
            existing.setFirstName(profile.getFirstName());
            existing.setLastName(profile.getLastName());
            existing.setPhone(profile.getPhone());
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
            return profileRepository.save(profile);
        }
    }

    public Profile getById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
    }

    public Profile getByUserId(Long userId) {
        return profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user"));
    }

    public void delete(Long id) {
        if (!profileRepository.existsById(id))
            throw new EntityNotFoundException("Profile not found");
        profileRepository.deleteById(id);
    }
}
