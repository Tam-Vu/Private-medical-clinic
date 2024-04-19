package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListRecordDetailDto {
    private Integer id;
    private Integer appointmentListRecordId;
    private Integer medicineId;
    private Integer quantity;
    private Integer usageId;
}
