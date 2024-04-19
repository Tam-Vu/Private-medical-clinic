package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.AppointmentListRecord;

import java.util.List;

@Repository
public interface AppointmentListRecordRepo extends JpaRepository<AppointmentListRecord, Long> {
      @Query("select m from AppointmentListRecord m join m.appointmentList join m.disease join m.patient")
      List<AppointmentListRecord> findAllAppointmentListRecord();
}
