package com.backend_API.Yarah.mvc.controller;

import com.backend_API.Yarah.listing.Listing;
import com.backend_API.Yarah.listing.ListingService;
import com.backend_API.Yarah.seller.Seller;
import com.backend_API.Yarah.seller.SellerService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/sellers")
public class SellerMVCController {
    
    private final SellerService sellerService;
    private final ListingService listingService;

    public SellerMVCController(SellerService sellerService, ListingService listingService) {
        this.sellerService = sellerService;
        this.listingService = listingService;
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


    @GetMapping("/createListing/New")
    public String newListingForm(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }

        Seller seller = sellerService.getSellerById(sellerId);
        model.addAttribute("seller", seller);
        return "seller/createListing";
    }

    @PostMapping("/Listing/new")
    public String createListing(@RequestParam String description,
                                @RequestParam String condition, 
                                @RequestParam String listingPhotoPath,
                                @RequestParam BigDecimal size,
                                @RequestParam BigDecimal weight,
                                @RequestParam BigDecimal price,
                                @RequestParam(defaultValue = "true") boolean available,
                                @RequestParam(defaultValue = "false") boolean sold,
                                HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }

        Listing listing = new Listing();
        listing.setDescription(description);
        listing.setCondition(condition);
        listing.setListingPhotoPath(listingPhotoPath);
        listing.setSize(size);
        listing.setWeight(weight);
        listing.setPrice(price);
        listing.setAvailable(available);
        listing.setSold(sold);

        listingService.createListing(listing);

        return "redirect:/sellers/sellerSelling";

    }

    @PostMapping("/Listing/edit")
    public String updateListing(@PathVariable Long id,
                                @RequestParam String description,
                                @RequestParam String condition, 
                                @RequestParam String listingPhotoPath,
                                @RequestParam BigDecimal size,
                                @RequestParam BigDecimal weight,
                                @RequestParam BigDecimal price,
                                @RequestParam(defaultValue = "true") boolean available,
                                @RequestParam(defaultValue = "false") boolean sold,
                                HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }

        Listing listing = new Listing();
        listing.setDescription(description);
        listing.setCondition(condition);
        listing.setListingPhotoPath(listingPhotoPath);
        listing.setSize(size);
        listing.setWeight(weight);
        listing.setPrice(price);
        listing.setAvailable(available);
        listing.setSold(sold);

        listingService.createListing(listing);

        return "redirect:/sellers/sellerSelling";
    }

}
