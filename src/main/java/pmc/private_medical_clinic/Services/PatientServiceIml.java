/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import io.micrometer.observation.ObservationHandler;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.PatientDto;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.PatientRepo;

/**
 *
 * @author duyth
 */
@Service
public class PatientServiceIml implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Patient createPatient(PatientDto patientDto) {
        Patient existingPatient = patientRepo.findPatientByPhoneNumber(patientDto.getPhoneNumber());
        if(existingPatient!=null)
            return existingPatient;
        
        Patient patient = new Patient();
        patient.setFullName(patientDto.getFullName());
        patient.setAddress(patientDto.getAddress());
        patient.setBirthYear(patientDto.getBirthYear());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setGender(patientDto.getGender());
        patientRepo.save(patient);
        return patient;
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).get();
    }

    @Override
    public Patient updatePatientById(Long id, PatientDto patientDto) {
        Patient patient = patientRepo.findById(id).get();
        if (patient != null) {
            patient.setFullName(patientDto.getFullName());
            patient.setAddress(patientDto.getAddress());
            patient.setBirthYear(patientDto.getBirthYear());
            patient.setPhoneNumber(patientDto.getPhoneNumber());
            patient.setGender(patientDto.getGender());
            return patientRepo.save(patient);
        }
        return null;
    }

    @Override
    public boolean deletePatientById(Long id) {
        if (patientRepo.findById(id) != null) {
            patientRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
