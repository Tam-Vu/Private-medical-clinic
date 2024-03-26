package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thymeleaf.spring6.processor.SpringActionTagProcessor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nguoidung")
public class User {
    @Id
    @Column(name="U_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long uId;
    @Column(length = 60, name = "ten_dang_nhap")
    private String tenDangNhap;
    @Column(length = 60, name = "mat_khau")
    private String matKhau;
    @Column(length = 60, name = "ho_ten")
    private String hoTen;
    @Column(length = 60, name = "Email")
    private String email;
    @Column(length = 60, name = "ma_nhom")
    private long maNhom;
}
