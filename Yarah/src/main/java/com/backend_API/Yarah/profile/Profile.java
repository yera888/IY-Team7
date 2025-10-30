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
    private Long id; 

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Size(max = 120)
    private String firstName;

    @Size(max = 120)
    private String lastName;

    @Size(max = 50)
    private String phone;

    @Column(length = 20)
    private String accountType; 

    private boolean locationEnabled = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Profile(User user) {
        this.user = user;
        this.id = user.getUserId();
        this.firstName = user.getName();
        this.lastName = "";
        this.phone = user.getPhoneNumber();
        this.accountType = "customer";
        this.locationEnabled = false;
    }
}
