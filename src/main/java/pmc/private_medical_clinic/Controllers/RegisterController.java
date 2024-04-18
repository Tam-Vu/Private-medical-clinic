package pmc.private_medical_clinic.Controllers;

import jakarta.annotation.security.PermitAll;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.PasswordDto;
import pmc.private_medical_clinic.Dto.ResetPassword;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ForgotPassword;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Services.ForgotPasswordService;
import pmc.private_medical_clinic.Services.JwtService;
import pmc.private_medical_clinic.Services.UserService;


@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/register")
    @ResponseBody
    public ResponeInfo<User> registerUser(@RequestBody UserDto userDto) {
        ResponeInfo<User> responeInfo = new ResponeInfo<>();
        try {
            User user = userService.registerUser(userDto);
            responeInfo.setData(user);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("create account successfully");
        } catch (Exception e) {
            responeInfo.setMessage(e.getMessage());
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }
}

