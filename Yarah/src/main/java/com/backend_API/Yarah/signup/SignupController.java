package com.backend_API.Yarah.signup;

import com.backend_API.Yarah.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SignupController {

    private final SignupService signupService;

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody SignupRequest req) {
        try {
            User u = new User();
            u.setName((req.getFirstName() + " " + req.getLastName()).trim());
            u.setEmail(req.getEmail());
            u.setPhoneNumber(req.getPhoneNumber());
            u.setAddress(req.getAddress());
            u.setPassword(req.getPassword());

            User saved = signupService.signup(u);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();
            if (msg != null && msg.contains("already in use")) {
                throw new ResponseStatusException(CONFLICT, msg);
            }
            throw new ResponseStatusException(BAD_REQUEST, msg);
        }
    }

    @Data
    public static class SignupRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String address;
        private String password;
    }
}
