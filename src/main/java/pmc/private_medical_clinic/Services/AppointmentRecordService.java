package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentRecord;

import java.util.List;

@Service
public interface AppointmentRecordService {

    List<AppointmentRecord> getAllAppointmentRecords();

    List<AppointmentRecord> getByPatientId(Long patientId);

    AppointmentRecord getRecordByAppointmentListPatientId(Long appointmentListPatientId);

    AppointmentRecord getRecordByBillId(Long billId);

    AppointmentRecord createAppointmentRecord(AppointmentRecordDto appointmentRecordDto);

    AppointmentRecord getAppointmentRecordById(Long id);

    AppointmentRecord updateAppointmentRecordById(Long id, AppointmentRecordDto appointmentRecordDto);

    AppointmentRecord deleteAppointmentRecordById(Long id);
}
