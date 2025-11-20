package com.backend_API.Yarah.seller;

import lombok.Data;

/**
 * DTO used by the API for creating/updating seller profiles.
 * Frontend will send JSON that maps to this shape.
 */
@Data
public class SellerProfileRequest {

    // Required for create
    private Long userId;

    private String shopName;
    private String bio;

    /**
     * You can send either a JSON string (e.g. serialized object)
     * or something simple like a bank / PayPal / Stripe identifier.
     * The column in DB is jsonb, but here we store as String.
     */
    private String payoutDetails;
}
