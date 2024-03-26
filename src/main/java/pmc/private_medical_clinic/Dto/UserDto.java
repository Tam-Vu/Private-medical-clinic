package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String tenDangNhap;
    private String matKhau;
    private String nhapLaiMatKhau;
    private String hoTen;
    private String email;
    private long maNhom;
}
