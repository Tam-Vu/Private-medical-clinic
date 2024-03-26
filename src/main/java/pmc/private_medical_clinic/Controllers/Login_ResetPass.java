package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Services.UserService;

public class Login_ResetPass {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/login")
    public String login(@RequestBody UserDto userDto ) {

        return "login";
    }

}
