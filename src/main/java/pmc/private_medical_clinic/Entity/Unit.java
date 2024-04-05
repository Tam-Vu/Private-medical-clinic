package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pmc.private_medical_clinic.Dto.UnitDto;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DONVI")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donViId;
    private String tenDonVi;
}
