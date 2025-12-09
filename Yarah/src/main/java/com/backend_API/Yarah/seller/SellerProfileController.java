package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/seller-profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SellerProfileController {

    private final SellerProfileService sellerProfileService;
    private final SellerProfileRepository sellerProfileRepository;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SellerProfile> get(@PathVariable Long id) {
        return sellerProfileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<SellerProfile> getByUser(@PathVariable Long userId) {
        try {
            SellerProfile sp = sellerProfileService.getByUserId(userId);
            return ResponseEntity.ok(sp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Get current user's seller profile
     */
    @GetMapping("/me")
    public ResponseEntity<SellerProfile> getMySellerProfile(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            SellerProfile sp = sellerProfileService.getByUserId(user.getUserId());
            return ResponseEntity.ok(sp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(NOT_FOUND, "No seller profile found");
        }
    }

    /**
     * Create seller profile for current user
     */
    @PostMapping("/create")
    public ResponseEntity<SellerProfile> create(
            Authentication auth,
            @RequestBody SellerProfileRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Override userId from request with authenticated user's id
        req.setUserId(user.getUserId());

        try {
            SellerProfile sp = sellerProfileService.createSellerProfile(req);
            return ResponseEntity.status(CREATED).body(sp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(CONFLICT, e.getMessage());
        }
    }

    /**
     * Update current user's seller profile
     */
    @PutMapping("/me")
    public ResponseEntity<SellerProfile> updateMine(
            Authentication auth,
            @RequestBody SellerProfileRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            SellerProfile existing = sellerProfileService.getByUserId(user.getUserId());
            SellerProfile updated = sellerProfileService.updateSellerProfile(
                existing.getSellerProfileId(), 
                req
            );
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Legacy endpoint - kept for backward compatibility
     */
    @PostMapping
    public ResponseEntity<SellerProfile> createLegacy(@RequestBody SellerProfileRequest req) {
        try {
            SellerProfile sp = sellerProfileService.createSellerProfile(req);
            return ResponseEntity.status(CREATED).body(sp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(CONFLICT, e.getMessage());
        }
    }
}