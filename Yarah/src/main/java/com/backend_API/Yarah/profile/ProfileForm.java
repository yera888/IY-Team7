package com.backend_API.Yarah.profile;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileForm {

    private Long profileId;
    private Long userId;

    @Size(max = 120)
    private String firstName;

    @Size(max = 120)
    private String lastName;

    private boolean locationEnabled;

    public static ProfileForm fromEntity(Profile p) {
        ProfileForm f = new ProfileForm();
        f.setProfileId(p.getProfileId());
        if (p.getUser() != null) {
            f.setUserId(p.getUser().getUserId());
        }
        f.setFirstName(p.getFirstName());
        f.setLastName(p.getLastName());
        f.setLocationEnabled(p.isLocationEnabled());
        return f;
    }
}
