package com.backend_API.Yarah.seller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerProfileMvcController {

    private final SellerProfileService sellerProfileService;

    @GetMapping("/register")
    public String showSellerForm(HttpSession session, Model model) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        if (currentUserId == null) {
            return "redirect:/login";
        }

        SellerProfileForm form = sellerProfileService.getFormForUser(currentUserId);
        model.addAttribute("sellerForm", form);
        return "seller-register";
    }

    @PostMapping("/register")
    public String handleSellerForm(
            HttpSession session,
            @Valid @ModelAttribute("sellerForm") SellerProfileForm form,
            BindingResult bindingResult,
            Model model
    ) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        if (currentUserId == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "seller-register";
        }

        form.setUserId(currentUserId);

        try {
            sellerProfileService.saveFromForm(form, currentUserId);
            return "redirect:/account";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "seller-register";
        }
    }
}
