package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.PrincipleDto;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.Principle;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.PrincipleRepo;
import pmc.private_medical_clinic.Services.CustomUserDetails;
import pmc.private_medical_clinic.Services.PrincipleService;
import pmc.private_medical_clinic.Services.UserService;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;

import java.security.Principal;

@Controller
@RequestMapping("/principle")
public class PrincipleController{

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipleRepo principleRepo;

    @Autowired
    private PrincipleService principleService;

    @Autowired
    UserDetailsService userDetailsService;

    @PutMapping("/")
    public String Test() {
        return "hello";
    }

    @GetMapping("/setting")
    public String getSetting(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = ((CustomUserDetails) userDetails).getUser();
        model.addAttribute("user", user);
        return "setting";
    }

    @PostMapping("/update-infomation")
    public String updateInfo(@ModelAttribute UserDto userDto, Principal principal) {
        User user = userService.updateInfo(userDto, principal);
        return "redirect:/principle/setting";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute UserDto userDto, Principal principal, Model model) {
        try {
            User user = userService.changePassword(userDto, principal);
            return "redirect:/principle/setting";
        } catch (IncorrectPasswordException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/principle/setting";
        }
    }

    @PostMapping("/change-principle")
    public String changePrinciple(@ModelAttribute PrincipleDto principleDto, Model model) {
        Principle principle = principleRepo.getPrincipleByThamSoId(1);
        model.addAttribute("principle", principle);
        Principle principle1 = principleService.updateDetails(principleDto);
        return "redirect:/principle/setting";
    }
}
