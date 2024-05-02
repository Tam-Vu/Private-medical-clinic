package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long thuocId;
    private String tenThuoc;
    private Long maDonVi;
    private long donGia;
    private long soLuong;
    private byte[] image;
    private boolean isDeleted;
}
