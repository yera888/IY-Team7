package com.backend_API.Yarah.auth;

import com.backend_API.Yarah.auth.dtos.AuthRequest;
import com.backend_API.Yarah.auth.dtos.AuthResponse;
import com.backend_API.Yarah.config.JwtUtil;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(AuthRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Bad credentials"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Bad credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail());
    }

    public void register(AuthRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User u = new User();
        u.setEmail(req.getEmail());
        u.setPhone(""); 
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        userRepository.save(u);
    }
}
