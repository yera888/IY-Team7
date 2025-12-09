package com.backend_API.Yarah.config;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import com.backend_API.Yarah.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Use email as username
            User user = userRepository.findByEmailIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Get profile to determine role
            var profile = profileRepository.findByUser(user).orElse(null);
            String role = (profile != null && profile.getAccountType() != null) 
                ? profile.getAccountType() 
                : "CUSTOMER";

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(role)
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(
                                "/",
                                "/Client-Side/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/api/signup/**",
                                "/api/listings",
                                "/api/listings/{id}",
                                "/api/reviews/for-profile/**"
                        ).permitAll()
                        
                        // Seller-only endpoints
                        .requestMatchers(
                                "/api/listings",
                                "/api/listings/{id}"
                        ).hasAnyRole("SELLER", "ADMIN")
                        
                        // Authenticated user endpoints
                        .requestMatchers(
                                "/api/profiles/me",
                                "/api/profiles/me/**",
                                "/api/seller-profiles/me",
                                "/api/seller-profiles/create",
                                "/api/chat/**",
                                "/api/reviews"
                        ).authenticated()
                        
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/Client-Side/login.html")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler((req, res, auth) -> {
                            res.setStatus(HttpStatus.OK.value());
                        })
                        .failureHandler((req, res, ex) -> {
                            res.setStatus(HttpStatus.UNAUTHORIZED.value());
                            res.getWriter().write("Invalid email or password");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((req, res, auth) -> 
                            res.setStatus(HttpStatus.OK.value())
                        )
                );

        return http.build();
    }
}