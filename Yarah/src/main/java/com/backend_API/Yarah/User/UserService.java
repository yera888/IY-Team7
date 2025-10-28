package com.backend_API.Yarah.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository UserRepository;
    
    public User updateUser(Long userId, User userDetails) {
        User user = UserRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setName(userDetails.getName());
        if (!user.getEmail().equals(userDetails.getEmail()) && 
            UserRepository.existsByEmail(userDetails.getEmail())) {
                throw new IllegalStateException("Email already registered");
        }
        user.setName(userDetails.getName());
        user.setAddress(userDetails.getAddress());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        return UserRepository.save(user);
    }
}
