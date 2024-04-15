/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pmc.private_medical_clinic.Repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;

/**
 *
 * @author duyth
 */
@Repository
public interface AppointmentListPatientRepo extends JpaRepository<AppointmentListPatient, Long> {
     @Query("SELECT al FROM AppointmentListPatient al JOIN al.patient p JOIN al.appointmentList l")
     List<AppointmentListPatient> findAllAppointmentList();
}
