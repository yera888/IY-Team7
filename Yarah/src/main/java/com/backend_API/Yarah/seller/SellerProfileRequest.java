package com.backend_API.Yarah.seller;

import lombok.Data;

/**
 * DTO used by the API for creating/updating seller profiles.
 * Frontend will send JSON that maps to this shape.
 */

@Data
public class SellerProfileRequest {
    private Long userId;
    private String shopName;
    private String bio;
    private String payoutDetails; // JSON or text, depending on your schema
}
