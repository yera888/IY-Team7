package com.backend_API.Yarah.signup;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignupService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

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

        // Adjust to your existing methods (existsByEmail vs existsByEmailIgnoreCase,
        // etc.)
        if (userRepository.existsByEmailIgnoreCase(incoming.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userRepository.existsByPhoneNumber(incoming.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already in use");
        }

        // prevent client from forcing ID
        incoming.setUserId(null);

        // hash the password
        String rawPassword = incoming.getPassword();
        String encoded = passwordEncoder.encode(rawPassword);
        incoming.setPassword(encoded);

        // save user
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

        // Create initial Profile as CUSTOMER
        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setAccountType("CUSTOMER"); // default type
        profile.setLocationEnabled(false); // hidden by default

        profileRepository.save(profile);

        return savedUser;
    }

    // Optional helper overload if you want to call signup with explicit args
    public User signup(String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String rawPassword) {

        User u = new User();
        u.setName((firstName + " " + lastName).trim());
        u.setEmail(email);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(address);
        u.setPassword(rawPassword);

        return signup(u);
    }
}
