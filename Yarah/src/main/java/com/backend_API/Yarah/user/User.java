package com.backend_API.Yarah.user;

import com.backend_API.Yarah.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "phone_number" })
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    // If you still want address here, keep it; otherwise remove & migrate schema.
    @NotBlank
    @Column(nullable = false)
    private String address;

    @JsonIgnore
    @NotBlank
    @Column(name = "password_hash", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // avoid infinite recursion
    private Profile profile;

    public User(Long userId) {
        this.userId = userId;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
        if (profile != null && profile.getUser() != this) {
            profile.setUser(this);
        }
    }
}
