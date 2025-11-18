package com.backend_API.Yarah.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setName(userDetails.getName());
        user.setShippingAddress(userDetails.getShippingAddress());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        return userRepository.save(user);
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<User> searchByAddress(String shippingAddress) {
        return userRepository.findByShippingAddressContaining(shippingAddress);
    }

    public List<User> searchByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumberContaining(phoneNumber);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
}