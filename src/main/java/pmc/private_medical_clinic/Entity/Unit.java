package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pmc.private_medical_clinic.Dto.UnitDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "units")
@Getter
@Setter
public class Unit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unitName")
    private String unitName;
}
