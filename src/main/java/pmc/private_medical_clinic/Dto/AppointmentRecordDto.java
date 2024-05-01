package pmc.private_medical_clinic.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRecordDto {
    private Long id;
    private Long patientId;
    private String symptoms;
    private Long diseaseId;
    private Long appointmentListId;
}
