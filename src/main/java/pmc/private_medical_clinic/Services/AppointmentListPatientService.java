/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListDto;
import pmc.private_medical_clinic.Dto.AppointmentListPatientDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;

/**
 *
 * @author duyth
 */
@Service
public interface AppointmentListPatientService {

    List<AppointmentListPatient> getAllAppointmentList();

    List<AppointmentListPatient> getByAppointmentListId(Long appointmentListId);

    List<AppointmentListPatient> getByPatientId(Long patientId);

    AppointmentListPatient createAppointmentListPatient(AppointmentListPatientDto appointmentListPatientDto);

    AppointmentListPatient getAppointmentListPatientById(Long id);

    boolean deleteAppointmentListPatient(Long id);

    AppointmentListPatient updateAppointmentListPatient(Long id, AppointmentListPatientDto appointmentListPatientDto);
    AppointmentListPatient moveAppointmentListPatientToTheEnd(Long id);
}
