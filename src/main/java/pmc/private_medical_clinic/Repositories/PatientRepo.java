/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pmc.private_medical_clinic.Entity.Patient;

/**
 *
 * @author duyth
 */
@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    @Query("select m from Patient m where m.phoneNumber = :phoneNumber")
    Patient findPatientByPhoneNumber(String phoneNumber);
    @Modifying
    @Transactional
    @Query("update Patient m set m.code = :otp where m.phoneNumber = :phoneNumber")
    void saveOTP(String phoneNumber, Long otp);
    @Query("select m from Patient m where m.phoneNumber = :phoneNumber and m.code = :otp")
    Patient findPatientByPhoneNumberAndCode(String phoneNumber, Long otp);

}
