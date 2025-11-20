package com.backend_API.Yarah.signup;

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
public class SignupService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public User signup(User incoming) {
        if (incoming == null) {
            throw new IllegalArgumentException("User payload required");
        }

        if (incoming.getEmail() == null || incoming.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email required");
        }

        if (incoming.getPhoneNumber() == null || incoming.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Phone number required");
        }

        if (incoming.getPassword() == null || incoming.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password required");
        }

        if (incoming.getAddress() == null || incoming.getAddress().isBlank()) {
            throw new IllegalArgumentException("Address required");
        }

        if (userRepository.existsByEmail(incoming.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.existsByPhoneNumber(incoming.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already in use");
        }

        // Make sure we don't accidentally reuse an ID from the client
        incoming.setUserId(null);

        User savedUser = userRepository.save(incoming);

        // Split full name into first + last for Profile
        String firstName = "";
        String lastName = "";
        if (savedUser.getName() != null && !savedUser.getName().isBlank()) {
            String[] parts = savedUser.getName().trim().split(" ", 2);
            firstName = parts[0];
            if (parts.length > 1) {
                lastName = parts[1];
            }
        }

        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setAccountType("CUSTOMER");
        profile.setLocationEnabled(false);

        profileRepository.save(profile);

        return savedUser;
    }
}
