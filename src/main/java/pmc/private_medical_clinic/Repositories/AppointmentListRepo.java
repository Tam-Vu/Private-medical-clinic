/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pmc.private_medical_clinic.Repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.AppointmentList;


/**
 *
 * @author duyth
 */
@Repository
public interface AppointmentListRepo extends JpaRepository<AppointmentList, Long>{
    @Query("select m from AppointmentList m where m.scheduleDate = :scheduleDate")
    AppointmentList findScheduleByDate(Date scheduleDate);
}
