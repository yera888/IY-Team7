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
    public ResponseEntity<Seller> createSeller (@Vaild @RequestBody Seller seller) {
        return ResponseEntity.ok(sellerService.createSeller(seller));
    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<Seller> updateSeller (@PathVariable Long sellerId, @Vaild @RequestBody Seller sellerDetails) {
        return ResponseEntity.ok(sellerService.updateSeller(sellerId, sellerDetails));
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<Seller> getSeller (@PathVariable Long sellerId) {
        return ResponseEntity.ok(sellerService.getSellerById(sellerId));
    }
}
