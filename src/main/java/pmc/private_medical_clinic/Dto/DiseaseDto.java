package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDto {
    private Long id;
    private String diseaseName;
}
