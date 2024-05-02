package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.Services.UserService;

@RestController
public class Login_ResetPass {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "welcome";
    }
}
