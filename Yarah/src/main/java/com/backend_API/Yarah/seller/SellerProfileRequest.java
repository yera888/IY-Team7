package com.backend_API.Yarah.seller;

import lombok.Data;

/**
 * DTO for creating/updating seller profiles.
 */
@Data
public class SellerProfileRequest {
    private Long userId;
    private String shopName;
    private String bio;
    private String payoutDetails;
    private Boolean locationEnabled;
    private String location;
}