package pmc.private_medical_clinic.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "THUOC")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long thuocId;
    @Column(length = 50)
    private String tenThuoc;
    private long donGia;
    private long soLuong;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    private boolean isDeleted;
    @ManyToOne (
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn (
            name = "maDonVi",
            referencedColumnName = "donViId"
    )
    private Unit unit;
}
