package com.backend_API.Yarah.user;


import com.backend_API.Yarah.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String address;

    private String phoneNumber;

    public User(Long id) {
        this.id = id;
    }

}