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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Dto.PasswordDto;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Services.UserService;


@Controller
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping("/register")
    public ResponeInfo<User> registerUser(@RequestBody UserDto userDto) {
    ResponeInfo<User> responeInfo = new ResponeInfo<>();
    try{
        User user = userService.registerUser(userDto);
        responeInfo.setData(user);
        responeInfo.setStatusCode(200);
        responeInfo.setErrorMessage(null);
    }
    catch (Exception e){
        responeInfo.setErrorMessage(e.getMessage());
        responeInfo.setStatusCode(500);
        responeInfo.setErrorMessage(e.getMessage());
    }
        return responeInfo;
    }

//    @GetMapping("/")
//    public String hello(Model model) {
//        model.addAttribute("hello", "hello world");
//        System.out.println("kết nối thành công");
//        return "login.html";
//    }

//    @PostMapping("/login")
//    public String login(@RequestBody UserDto userDto) {
//
//        boolean email = userService.checkByEmail(userDto.getEmail());
//        boolean username = userService.checkByTenDangNhap(userDto.getTenDangNhap());
//        boolean password = userService.checkPassword(userDto.getEmail(), userDto.getMatKhau());
//         if(!(email||username)) {
//            return "sai tai khoan hoac email";
//         }
//         if(password) {
//            return "dang nhap thanh cong";
//         }
//            return "hello";
//    }
}

