/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Patient;

/**
 *
 * @author duyth
 */
@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    @Query("select m from Patient m where m.phoneNumber = :phoneNumber")
    Patient findPatientByPhoneNumber(String phoneNumber);

}
