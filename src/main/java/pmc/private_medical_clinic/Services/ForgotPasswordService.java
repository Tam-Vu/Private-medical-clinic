package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.ResetPassword;
import pmc.private_medical_clinic.Entity.ForgotPassword;
@Service

public interface ForgotPasswordService {
    ForgotPassword sendEmail(String email);

    ForgotPassword verifyOtp(Integer otp, String email);

    void changPassword(ResetPassword resetPassword, String email);
}
