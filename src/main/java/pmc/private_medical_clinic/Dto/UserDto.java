package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String newPassword;
    private String repeatNewPassword;
    private String hoTen;
    private String email;
    private long maNhom;
    private String gioiTinh;
    private boolean isActive;
}
