package com.backend_API.Yarah.User.dtos;

import lombok.Data;

@Data
public class ProfileDto {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private Long defaultAddressId;
}
