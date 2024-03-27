package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Services.UserService;

@Controller
public class Login_ResetPass {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/login")
    public String login() {

        return "login";
    }

}
