package com.backend_API.Yarah.seller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService SellerService;

    @PostMapping
    public ResponseEntity<Seller> createSeller (@Valid @RequestBody Seller seller) {
        return ResponseEntity.ok(SellerService.createSeller(seller));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller (@PathVariable Long id) {
        return ResponseEntity.ok(SellerService.getSellerById(id));
    }
}
