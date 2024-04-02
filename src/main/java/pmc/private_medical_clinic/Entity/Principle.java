package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "THAMSO")
public class Principle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thamSoId;
    @Column(name = "so_benh_nhan_toi_da")
    private long soBenhNhanToiDa;
    @Column(name = "tien_kham")
    private long tienKham;
}
