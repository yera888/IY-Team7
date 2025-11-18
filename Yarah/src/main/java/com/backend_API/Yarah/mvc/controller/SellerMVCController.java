package com.backend_API.Yarah.mvc.controller;

import com.backend_API.Yarah.seller.Seller;
import com.backend_API.Yarah.seller.SellerService;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/sellers")
public class SellerMVCController {
    
    private final SellerService sellerService;

    public SellerMVCController(SellerService sellerService) {
        this.sellerService = sellerService;
    } 
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "seller/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Seller seller) {
        sellerService.createSeller(seller);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Seller seller = sellerService.authenticate(email, password);
            session.setAttribute("sellerId", seller.getId());
            return "redirect:/sellers/dashboard";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);
        model.addAttribute("seller", seller);
        return "seller/dashboard";
    }

}
