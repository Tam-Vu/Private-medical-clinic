/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import io.micrometer.observation.ObservationHandler;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.OTPBody;
import pmc.private_medical_clinic.Dto.PatientDto;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.PatientRepo;
import pmc.private_medical_clinic.utils.SendSMSUtils;

import javax.swing.*;

/**
 *
 * @author duyth
 */
@Service
public class PatientServiceIml implements PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientServiceIml.class);
    @Autowired
    private PatientRepo patientRepo;
    private final SendSMSUtils sendSMSUtils = new SendSMSUtils();

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Patient createPatient(PatientDto patientDto) {
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

    @Override
    public Patient getPatientByPhoneNumber(String phoneNumber) {
        Patient existingPatient = patientRepo.findPatientByPhoneNumber(phoneNumber);
        return existingPatient;
    }

    @Override
    public boolean validPhoneNumber(String phoneNumber) {
        Patient existingPatient = patientRepo.findPatientByPhoneNumber(phoneNumber);
        if (existingPatient != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean sendOTP(String phoneNumber) {
        try {
            OTPBody otpBody = new OTPBody();
            Random rand = new Random();
            int randomNum = rand.nextInt((999999 - 100000) + 1) + 100000;
            otpBody.setMessage("Your OTP is: " + randomNum);
            sendSMSUtils.sendSMS(phoneNumber, otpBody);
            patientRepo.saveOTP(phoneNumber, (long)randomNum);
            return true;
        }
        catch (Exception e) {
            logger.error("Error sending OTP", e);
            return false;

        }
    }

    @Override
    public boolean verifyOTP(String phoneNumber, Long OTP) {
        Patient existingPatient = patientRepo.findPatientByPhoneNumberAndCode(phoneNumber, OTP);
        if (existingPatient != null) {
            return true;
        }
        return false;
    }

}
