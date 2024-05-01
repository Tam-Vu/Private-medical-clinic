package pmc.private_medical_clinic.Dto;

import lombok.*;

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArgumentDto {
    private Long id;
    private Long maxNumberOfPatients;
    private Long feeConsult;
}
