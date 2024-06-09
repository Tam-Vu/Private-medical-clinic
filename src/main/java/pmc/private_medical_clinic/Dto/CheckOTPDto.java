package pmc.private_medical_clinic.Dto;

import lombok.*;

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CheckOTPDto {
    String phoneNumber;
    Long otp;
}
