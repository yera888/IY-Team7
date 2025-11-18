package com.backend_API.Yarah.auth;

import com.backend_API.Yarah.signup.SignupService;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserService;
import com.backend_API.Yarah.user.UserRepository;
import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthMvcController {

    private final SignupService signupService;
    private final UserService userService;
    private final ProfileService profileService;
    private final UserRepository userRepository;

    // ---------- SIGNUP ----------

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(
            @Valid @ModelAttribute("user") User formUser,
            BindingResult bindingResult,
            HttpSession session,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            User saved = signupService.signup(formUser);
            session.setAttribute("currentUserId", saved.getUserId());
            return "redirect:/account";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "signup";
        }
    }

    // ---------- LOGIN ----------

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult bindingResult,
            HttpSession session,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        return userRepository.findByEmailAndPassword(
                        loginForm.getEmail(),
                        loginForm.getPassword()
                )
                .map(user -> {
                    session.setAttribute("currentUserId", user.getUserId());
                    return "redirect:/account";
                })
                .orElseGet(() -> {
                    model.addAttribute("errorMessage", "Invalid email or password");
                    return "login";
                });
    }

    // ---------- LOGOUT ----------

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ---------- ACCOUNT PAGE ----------

    @GetMapping("/account")
    public String accountPage(HttpSession session, Model model) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        if (currentUserId == null) {
            return "redirect:/login";
        }

        User user = userService.getById(currentUserId);
        Profile profile = profileService.getByUserId(currentUserId);

        model.addAttribute("user", user);
        model.addAttribute("profile", profile);

        return "account-profile";
    }
}
