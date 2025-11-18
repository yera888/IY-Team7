package com.backend_API.Yarah.signup.controller;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserMVCController {
    private final UserService userService;


    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            User user = userService.authenticate(email, password);
            session.setAttribute("userId", user.getId());
            return "redirect:/sellers/dashboard";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/signin";
        }
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "seller/dashboard";
    }
}
