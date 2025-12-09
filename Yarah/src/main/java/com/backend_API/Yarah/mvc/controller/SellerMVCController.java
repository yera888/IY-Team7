package com.backend_API.Yarah.mvc.controller;

import com.backend_API.Yarah.listing.Listing;
import com.backend_API.Yarah.listing.ListingService;
import com.backend_API.Yarah.seller.Seller;
import com.backend_API.Yarah.seller.SellerService;
import com.backend_API.Yarah.file.FileStorageService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sellers")
public class SellerMVCController {
    
    private final SellerService sellerService;
    private final ListingService listingService;
    private final FileStorageService fileStorageService;

    public SellerMVCController(SellerService sellerService, ListingService listingService, FileStorageService fileStorageService) {
        this.sellerService = sellerService;
        this.listingService = listingService;
        this.fileStorageService = fileStorageService;
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

    @GetMapping("/sellerSelling")
    public String sellerSelling(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);
        List<Listing> listings = listingService.getListingBySeller(seller).stream()
            .filter(Listing::getAvailable)
            .filter(l -> !l.getSold())
            .collect(Collectors.toList());

        model.addAttribute("seller", seller);
        model.addAttribute("listings",listings);

        return "seller/sellerSelling";
    }

    @GetMapping("/sellerBalance")
    public String sellerBalance(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);

        model.addAttribute("seller", seller);

        return "seller/sellerBalance";
    }

    @GetMapping("/sellerStats")
    public String sellerStats(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);

        model.addAttribute("seller", seller);

        return "seller/sellerStats";
    }

    @GetMapping("/sellerSold")
    public String sellerSold(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);
        List<Listing> allListings = listingService.getAllListingsBySeller(seller);

        List<Listing> listings = allListings.stream()
            .filter(Listing::getSold)
            .collect(Collectors.toList());

        model.addAttribute("seller", seller);
        model.addAttribute("listings", listings);

        return "seller/sellerSold";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);
        List<Listing> allListings = listingService.getAllListingsBySeller(seller);

        List<Listing> sellingListings = allListings.stream()
            .filter(Listing::getAvailable)
            .filter(l -> !l.getSold())
            .collect(Collectors.toList());

        List<Listing> soldListings = allListings.stream()
            .filter(Listing::getSold)
            .collect(Collectors.toList());

        model.addAttribute("seller", seller);
        model.addAttribute("sellingListings", sellingListings);
        model.addAttribute("soldListings", soldListings);
        model.addAttribute("totalListings", allListings.size());

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
        model.addAttribute("title", "Create New Listing");
        return "seller/createListing";
    }

    @PostMapping("/Listing/new")
    public String createListing(@RequestParam String description,
                                @RequestParam String condition, 
                                @RequestParam(required = false) MultipartFile[] photos,
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

        Seller seller = sellerService.getSellerById(sellerId);
        Listing listing = new Listing();

        listing.setSeller(seller);
        listing.setDescription(description);
        listing.setCondition(condition);

        String photoUrls = fileStorageService.storeFiles(photos);
        listing.setListingPhotoPath(photoUrls);

        listing.setSize(size);
        listing.setWeight(weight);
        listing.setPrice(price);
        listing.setAvailable(available);
        listing.setSold(sold);

        listingService.createListing(listing);

        return "redirect:/sellers/sellerSelling";

    }

    @GetMapping("/editListing")
    public String editListing(HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Seller seller = sellerService.getSellerById(sellerId);
        List<Listing> listings = listingService.getListingBySeller(seller);

        model.addAttribute("seller", seller);
        model.addAttribute("listings", listings);
        
        return "seller/editListing";
    }

    @GetMapping("/editListing/{id}")
    public String editExistingListing(@PathVariable Long id, HttpSession session, Model model) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }
        Listing listing = listingService.getListingById(id);

        if (!listing.getSeller().getId().equals(sellerId)) {
            return "redirect:/sellers/sellerSelling?error=unauthorized";
        }

        model.addAttribute("listing", listing);
        model.addAttribute("title", "Edit Your Listing");

        return "seller/editListing";
    }

    @PostMapping("/Listing/edit/{id}")
    public String updateListing(@PathVariable Long id,
                                @RequestParam String description,
                                @RequestParam String condition, 
                                @RequestParam(required = false) MultipartFile[] photos,
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

        Listing listing = listingService.getListingById(id);
        listing.setDescription(description);
        listing.setCondition(condition);

        if (photos != null && photos.length > 0) {
            boolean hasValidFiles = false;
            for (MultipartFile photo : photos) {
                if (photo != null && !photo.isEmpty()) {
                    hasValidFiles = true;
                    break;
                }
            }
            
            if (hasValidFiles) {
                String photoUrls = fileStorageService.storeFiles(photos);
                listing.setListingPhotoPath(photoUrls);
            }
        }
    
        listing.setSize(size);
        listing.setWeight(weight);
        listing.setPrice(price);
        listing.setAvailable(available);
        listing.setSold(sold);

        listingService.updateListing(id, listing);

        return "redirect:/sellers/sellerSelling";
    }

    @PostMapping("/Listing/delete/{id}")
    public String deleteListing(@PathVariable Long id, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/signin";
        }

        // Verify the listing belongs to this seller before deleting
        Listing listing = listingService.getListingById(id);
        if (!listing.getSeller().getId().equals(sellerId)) {
            return "redirect:/sellers/sellerSelling?error=unauthorized";
        }

        listingService.deleteListing(id);
        return "redirect:/sellers/sellerSelling";
}
}
