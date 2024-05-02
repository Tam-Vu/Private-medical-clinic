package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListRecordDetailDto {
    private Long id;
    private Long appointmentRecordId;
    private Long drugId;
    private Long count;
    private Long usageId;
}
