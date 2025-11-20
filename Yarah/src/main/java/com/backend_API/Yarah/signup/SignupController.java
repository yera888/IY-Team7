package com.backend_API.Yarah.signup;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SignupService signupService;

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody User user) {
        User savedUser = signupService.signup(user);
        // Password is @JsonIgnore so it won't be sent back
        return ResponseEntity.status(201).body(savedUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<Profile> getProfileByUser(@PathVariable Long userId) {
        Profile profile = profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user"));
        return ResponseEntity.ok(profile);
    }
}
