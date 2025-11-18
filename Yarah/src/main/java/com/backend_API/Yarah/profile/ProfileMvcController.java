package com.backend_API.Yarah.profile;

import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileMvcController {

    private final ProfileService profileService;
    private final UserService userService;

    // GET /profiles/{id}/edit
    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable("id") Long profileId,
            HttpSession session,
            Model model
    ) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        if (currentUserId == null) {
            return "redirect:/login";
        }

        Profile p = profileService.getById(profileId);

        // only allow editing own profile
        User owner = p.getUser();
        if (owner == null || !owner.getUserId().equals(currentUserId)) {
            return "redirect:/account";
        }

        ProfileForm form = ProfileForm.fromEntity(p);
        model.addAttribute("profileForm", form);

        return "profile-edit"; // templates/profile-edit.ftlh
    }

    // POST /profiles/{id}/edit
    @PostMapping("/{id}/edit")
    public String handleEdit(
            @PathVariable("id") Long profileId,
            HttpSession session,
            @Valid @ModelAttribute("profileForm") ProfileForm form,
            BindingResult bindingResult,
            Model model
    ) {
        Long currentUserId = (Long) session.getAttribute("currentUserId");
        if (currentUserId == null) {
            return "redirect:/login";
        }

        Profile p = profileService.getById(profileId);
        User owner = p.getUser();
        if (owner == null || !owner.getUserId().equals(currentUserId)) {
            return "redirect:/account";
        }

        if (bindingResult.hasErrors()) {
            return "profile-edit";
        }

        // update allowed fields
        p.setFirstName(form.getFirstName());
        p.setLastName(form.getLastName());
        p.setLocationEnabled(form.isLocationEnabled());

        profileService.createOrUpdate(p);

        return "redirect:/account";
    }
}
