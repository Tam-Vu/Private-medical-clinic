package pmc.private_medical_clinic.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {
    private Long id;
    private String drugName;
    private Long price;
    private Long count;
    private Long unitId;
    private String note;
}
