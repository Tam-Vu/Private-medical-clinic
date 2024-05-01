package pmc.private_medical_clinic.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageDto {
    private Long id;
    private String usageDes;
}
