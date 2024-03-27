package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private String tenThuoc;
    private int maDonVi;
    private long donGia;
    private long soLuong;
    private String image;
    private boolean isDeleted;
}
