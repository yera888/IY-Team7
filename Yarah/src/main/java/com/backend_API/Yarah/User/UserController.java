package com.backend_API.Yarah.User;

import com.csc340340.backend_API.Yarah.customer.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
import java.util.List;

public class UserController {
    private final UserService userService;
    private final CustomerServie customerService;

    @PostMapping 
    public ResponseEntity<User> createUser(@Valid)

}
