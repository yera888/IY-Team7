package com.backend_API.Yarah.seller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerProfileForm {

    private Long sellerProfileId;
    private Long userId;

    @NotBlank(message = "Shop name is required")
    private String shopName;

    private String bio;

    private boolean locationEnabled;

    private String payoutDetails;

    public static SellerProfileForm fromEntity(SellerProfile sp, boolean locationEnabled) {
        SellerProfileForm f = new SellerProfileForm();
        f.setSellerProfileId(sp.getSellerProfileId());
        f.setUserId(sp.getUser().getUserId());
        f.setShopName(sp.getShopName());
        f.setBio(sp.getBio());
        f.setPayoutDetails(sp.getPayoutDetails());
        f.setLocationEnabled(locationEnabled);
        return f;
    }
}
