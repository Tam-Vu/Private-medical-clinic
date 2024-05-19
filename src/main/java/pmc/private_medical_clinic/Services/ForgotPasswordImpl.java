package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.MailBody;
import pmc.private_medical_clinic.Dto.ResetPassword;
import pmc.private_medical_clinic.Entity.ForgotPassword;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.ForgotPasswordRepo;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.failureHandler.GlobalExceptionHandler;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service

public class ForgotPasswordImpl implements ForgotPasswordService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ForgotPassword sendEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new GlobalExceptionHandler("email not found"));
        if (user.isActive() == false) {
            throw new GlobalExceptionHandler("Username not active");
        }
        Integer otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("this is a code for you to reset your password: " + otp)
                .subject("OTP for reset password")
                .build();
        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //10p thì otp hết hạn
                .user(user)
                .build();
        forgotPasswordRepo.save(forgotPassword);
        emailService.sendSimpleMessage(mailBody);
        return forgotPassword;
    }

    @Override
    public ForgotPassword verifyOtp(Integer otp, String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new GlobalExceptionHandler("email not found"));
        ForgotPassword fp = forgotPasswordRepo.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new GlobalExceptionHandler("Invalid Otp for email: " + email));
        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepo.deleteById(fp.getFpid());
            throw new GlobalExceptionHandler("OTP has expired");
        }
        forgotPasswordRepo.deleteById(fp.getFpid());
        return fp;
    }

    @Override
    public void changPassword(ResetPassword resetPassword, String email) {
        if (!Objects.equals(resetPassword.getNewPassword(), resetPassword.getRepeatNewPassword())) {
            throw new GlobalExceptionHandler("Wrong repeat new password");
        }
        String encoderedPass = passwordEncoder.encode(resetPassword.getNewPassword());
        userRepo.updatePassword(email, encoderedPass);
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
