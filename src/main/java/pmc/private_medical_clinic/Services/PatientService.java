/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.PatientDto;
import pmc.private_medical_clinic.Entity.Patient;

/**
 *
 * @author duyth
 */
@Service
public interface PatientService {
    List<Patient> getAllPatients();
    Patient createPatient(PatientDto patient);
    Patient getPatientById(Long id);
    Patient updatePatientById(Long id, PatientDto patientDto);
    boolean deletePatientById(Long id);
    Patient getPatientByPhoneNumber(String phoneNumber);
    boolean validPhoneNumber(String phoneNumber);
    boolean sendOTP(String phoneNumber);
    boolean verifyOTP(String phoneNumber, Long OTP);

}
