/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListPatientDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.AppointmentListPatientRepo;

/**
 *
 * @author duyth
 */
@Service
public class AppointmentListPatientServiceIml implements AppointmentListPatientService {

    @Autowired
    AppointmentListPatientRepo appointmentListPatientRepo;

    @Override
    public List<AppointmentListPatient> getAllAppointmentList() {
        return (List<AppointmentListPatient>) appointmentListPatientRepo.findAllAppointmentList();
    }

    @Override
    public AppointmentListPatient createAppointmentListPatient(AppointmentListPatientDto appointmentListPatientDto) {
        AppointmentListPatient appointmentListPatient = new AppointmentListPatient();
        Integer maxOrderNumber = appointmentListPatientRepo.findMaxOrderNumberByDateId(appointmentListPatientDto.getAppointmentListId());
        if (maxOrderNumber == null) {
            appointmentListPatient.setOrderNumber(1);
        } else {
            appointmentListPatient.setOrderNumber(maxOrderNumber + 1);
        }
        Patient patient = new Patient();
        patient.setId(appointmentListPatientDto.getPatientId());
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setId(appointmentListPatientDto.getAppointmentListId());
        appointmentListPatient.setAppointmentList(appointmentList);
        appointmentListPatient.setPatient(patient);
        appointmentListPatient.setTimeUpdate(new Date());
        appointmentListPatientRepo.save(appointmentListPatient);
        return appointmentListPatient;
    }

    @Override
    public AppointmentListPatient getAppointmentListPatientById(Long id) {
        return appointmentListPatientRepo.findById(id).get();
    }

    @Override
    public boolean deleteAppointmentListPatient(Long id) {
        if (appointmentListPatientRepo.findById(id) != null) {
            appointmentListPatientRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AppointmentListPatient updateAppointmentListPatient(Long id, AppointmentListPatientDto appointmentListPatientDto) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientRepo.findById(id).get();
        Integer maxOrderNumber = appointmentListPatientRepo.findMaxOrderNumberByDateId(appointmentListPatientDto.getAppointmentListId());
        if (maxOrderNumber == null) {
            appointmentListPatient.setOrderNumber(1);
        } else {
            appointmentListPatient.setOrderNumber(maxOrderNumber + 1);
        }
        Patient patient = new Patient();
        patient.setId(appointmentListPatientDto.getPatientId());

        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setId(appointmentListPatientDto.getAppointmentListId());

        if (appointmentListPatient != null) {
            appointmentListPatient.setAppointmentList(appointmentList);
            appointmentListPatient.setTimeUpdate(new Date());
            return appointmentListPatientRepo.save(appointmentListPatient);
        }
        return null;
    }

    @Override
    public List<AppointmentListPatient> getByAppointmentListId(Long appointmentListId) {
        return (List<AppointmentListPatient>) appointmentListPatientRepo.findByAppointmentListId(appointmentListId);
    }

    @Override
    public List<AppointmentListPatient> getByPatientId(Long patientId) {
        return (List<AppointmentListPatient>) appointmentListPatientRepo.findByPatientId(patientId);
    }

    @Override
    public AppointmentListPatient moveAppointmentListPatientToTheEnd(Long id) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientRepo.findById(id).get();
        if (appointmentListPatient != null) {
            appointmentListPatient.setTimeUpdate(new Date());
            return appointmentListPatientRepo.save(appointmentListPatient);
        }
        return null;
    }

}
