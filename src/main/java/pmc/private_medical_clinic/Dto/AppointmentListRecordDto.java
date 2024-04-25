package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListRecordDto {
    private Integer id;
    private Integer patientId;
    private String symptoms;
    private Integer diseaseId;
    private Integer appointmentListId;
}
