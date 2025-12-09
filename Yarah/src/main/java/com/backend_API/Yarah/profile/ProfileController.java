package com.backend_API.Yarah.profile;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;
    private final UserRepository userRepository;

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Profile> getByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return ResponseEntity.ok(profile);
    }

    /**
     * Get current user's profile
     */
    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return ResponseEntity.ok(profile);
    }

    /**
     * Update current user's profile
     */
    @PutMapping("/me")
    public ResponseEntity<Profile> updateMyProfile(
            Authentication auth,
            @RequestBody ProfileUpdateRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Profile updated = profileService.updateProfile(
            profile.getProfileId(),
            req.getFirstName(),
            req.getLastName(),
            req.getLocationEnabled(),
            req.getLocation()
        );

        return ResponseEntity.ok(updated);
    }

    /**
     * Update location settings
     */
    @PutMapping("/me/location")
    public ResponseEntity<Profile> updateLocation(
            Authentication auth,
            @RequestBody LocationUpdateRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Profile updated = profileService.updateLocation(
            profile.getProfileId(),
            req.isEnabled(),
            req.getLocation()
        );

        return ResponseEntity.ok(updated);
    }

    @Data
    public static class ProfileUpdateRequest {
        private String firstName;
        private String lastName;
        private Boolean locationEnabled;
        private String location;
    }

    @Data
    public static class LocationUpdateRequest {
        private boolean enabled;
        private String location;
    }
}
