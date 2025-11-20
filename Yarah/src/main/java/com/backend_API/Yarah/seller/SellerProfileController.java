package com.backend_API.Yarah.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<SellerProfile> create(@RequestBody SellerProfileRequest req) {
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
