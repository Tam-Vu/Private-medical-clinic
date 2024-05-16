package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDto {

    private String groupName;
    private String note;
    private boolean isActive;
}
