package pmc.private_medical_clinic.Controllers;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.*;
import pmc.private_medical_clinic.Entity.*;
import pmc.private_medical_clinic.Services.*;

import static org.hibernate.internal.CoreLogging.logger;
import static org.hibernate.internal.CoreLogging.messageLogger;
import static pmc.private_medical_clinic.Configs.Oauth2Config.authResponse;

@RequestMapping("/auth")
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Login_ResetPass {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/login")
    @ResponseBody
    public ResponeInfo<AuthResponse> login(@RequestBody LoginDto loginDto) {
        ResponeInfo<AuthResponse> responeInfo = new ResponeInfo<>();
        try {
            AuthResponse checkLogin = authService.login(loginDto);
            responeInfo.setData(checkLogin);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("passed login page");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/forgot-password/verify-mail/{email}")
    @ResponseBody
    public ResponeInfo<ForgotPassword> verifyEmail(@PathVariable("email") String email) {
        ResponeInfo<ForgotPassword> responeInfo = new ResponeInfo<>();
        try {
            ForgotPassword fp = forgotPasswordService.sendEmail(email);
            responeInfo.setData(fp);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("sent the email");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/verify-otp/{otp}/{email}")
    @ResponseBody
    public ResponeInfo<ForgotPassword> verifyOtpController(@PathVariable("otp") Integer otp, @PathVariable("email") String email) {
        ResponeInfo<ForgotPassword> responeInfo = new ResponeInfo<>();
        try {
            ForgotPassword fp = forgotPasswordService.verifyOtp(otp, email);
            responeInfo.setData(fp);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("verify otp successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/reset-password/{email}")
    @ResponseBody
    public ResponeInfo<String> changePass(@RequestBody ResetPassword resetPassword, @PathVariable("email") String email) {
        ResponeInfo<String> responeInfo = new ResponeInfo<>();
        try {
            forgotPasswordService.changPassword(resetPassword, email);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("Password has been change");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponeInfo<AuthResponse> logout(@RequestBody LogoutRequest request) {
        ResponeInfo<AuthResponse> responeInfo = new ResponeInfo<>();
        try {
            authService.logout(request);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("logout successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        if (authResponse != null) {
            authResponse = null;
        }
        return responeInfo;
    }

    @PostMapping("/refresh")
    @ResponseBody
    public ResponeInfo<AuthResponse> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        ResponeInfo<AuthResponse> responeInfo = new ResponeInfo<>();
        try {
            AuthResponse refreshAccToken = new AuthResponse();
            RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenDto.getRefreshToken());
            User user = refreshToken.getUser();
            String accessToken = jwtService.generateToken(user);

            refreshAccToken.setAccessToken(accessToken);
            refreshAccToken.setRefreshToken(refreshToken.getRefreshToken());

            responeInfo.setData(refreshAccToken);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("refreshed refresh token");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/success")
    @ResponseBody
    public ResponeInfo<AuthResponse> loginSuccessWithGoogle() {
        ResponeInfo<AuthResponse> responeInfo = new ResponeInfo<>();
        if (authResponse != null) {
            try {

                responeInfo.setData(authResponse);
                responeInfo.setStatusCode(200);
                responeInfo.setMessage("passed login page");
            } catch (Exception e) {
                responeInfo.setStatusCode(500);
                responeInfo.setMessage(e.getMessage());
            }
        }

        return responeInfo;
    }
}
