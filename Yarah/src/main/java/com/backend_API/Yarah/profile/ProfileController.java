package com.backend_API.Yarah.profile;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Profile> getByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        return ResponseEntity.ok(profile);
    }
}
