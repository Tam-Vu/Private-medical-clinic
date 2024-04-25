package pmc.private_medical_clinic.Services;

import pmc.private_medical_clinic.Dto.AppointmentListRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentListRecord;

import java.util.List;

public interface AppointmentListRecordService {
    List<AppointmentListRecord> getAllAppointmentListRecord();
    AppointmentListRecord createAppointmentListRecord(AppointmentListRecordDto appointmentListRecord);
    AppointmentListRecord getAppointmentListRecordById(Long id);
    AppointmentListRecord updateAppointmentListRecord(Long id, AppointmentListRecordDto appointmentListRecord);
    boolean deleteAppointmentListRecord(Long id);

}
