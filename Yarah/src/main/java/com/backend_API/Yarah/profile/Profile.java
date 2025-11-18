package com.backend_API.Yarah.profile;

import com.backend_API.Yarah.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, unique = true)
    private User user;

    @Size(max = 120)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 120)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "account_type", length = 20)
    private String accountType = "CUSTOMER"; // CUSTOMER | SELLER | ADMIN

    @Column(name = "location_enabled")
    private boolean locationEnabled = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Profile(User user) {
        this.user = user;
        this.firstName = user != null ? user.getName() : "";
        this.lastName = "";
        this.accountType = "CUSTOMER";
        this.locationEnabled = false;
    }
}
