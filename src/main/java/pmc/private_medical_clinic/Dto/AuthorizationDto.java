package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDto {
    private Long userGroupId;
    private Long featId;
    private Boolean isAccess;
}
