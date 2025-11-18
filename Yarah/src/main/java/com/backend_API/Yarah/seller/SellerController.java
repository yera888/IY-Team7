package com.backend_API.Yarah.seller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> createSeller (@Valid @RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.createSeller(seller));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @Valid @RequestBody Seller sellerDetails) {
        return ResponseEntity.ok(sellerService.updateSeller(id, sellerDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller (@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.getSellerById(id));
    }
}
