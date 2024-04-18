package pmc.private_medical_clinic.Dto;

import lombok.Data;

@Data
public class ResetPassword {
    private String newPassword;
    private String repeatNewPassword;
}
