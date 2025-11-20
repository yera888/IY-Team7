package com.backend_API.Yarah.seller;

import com.backend_API.Yarah.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/seller-profiles")
@RequiredArgsConstructor
public class SellerProfileController {

    private final SellerProfileService sellerProfileService;

    /**
     * Create a new seller profile for a user.
     *
     * Expected JSON payload:
     * {
     * "userId": 1,
     * "shopName": "My Shop",
     * "bio": "Vintage clothes",
     * "payoutDetails": "{ \"method\": \"venmo\", \"handle\": \"@user\" }"
     * }
     */
    @PostMapping
    public ResponseEntity<SellerProfile> create(@Valid @RequestBody SellerProfileRequest request) {
        try {
            if (request.getUserId() == null) {
                throw new IllegalArgumentException("userId is required");
            }

            SellerProfile sellerProfile = new SellerProfile();
            sellerProfile.setUser(new User(request.getUserId()));
            sellerProfile.setShopName(request.getShopName());
            sellerProfile.setBio(request.getBio());
            sellerProfile.setPayoutDetails(request.getPayoutDetails());

            SellerProfile created = sellerProfileService.createOrUpdate(sellerProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (EntityNotFoundException ex) {
            // if user or profile not found inside the service
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    /**
     * Update an existing seller profile (shop name, bio, payout details).
     * We DO NOT allow changing which user owns this profile.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SellerProfile> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SellerProfileRequest request) {

        try {
            SellerProfile existing = sellerProfileService.getById(id);

            if (request.getShopName() != null) {
                existing.setShopName(request.getShopName());
            }
            if (request.getBio() != null) {
                existing.setBio(request.getBio());
            }
            if (request.getPayoutDetails() != null) {
                existing.setPayoutDetails(request.getPayoutDetails());
            }

            SellerProfile updated = sellerProfileService.createOrUpdate(existing);
            return ResponseEntity.ok(updated);

        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerProfile> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(sellerProfileService.getById(id));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    /**
     * Get seller profile for the currently logged-in user (or any user id).
     * Your frontend can use the yarahUserId from localStorage here.
     */
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<SellerProfile> getByUser(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(sellerProfileService.getByUserId(userId));
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping
    public ResponseEntity<List<SellerProfile>> getAll() {
        return ResponseEntity.ok(sellerProfileService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            sellerProfileService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
