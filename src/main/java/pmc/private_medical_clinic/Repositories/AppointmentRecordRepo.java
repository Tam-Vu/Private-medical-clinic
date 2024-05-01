package pmc.private_medical_clinic.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.AppointmentRecord;

@Repository
public interface AppointmentRecordRepo extends JpaRepository<AppointmentRecord, Long> {

    @Query("SELECT al FROM AppointmentRecord al JOIN al.patient p JOIN al.appointmentList l WHERE p.id = :patientId AND l.id = :appointmentListId")
    AppointmentRecord findRecordByAppointmentListPatientId(Long patientId, Long appointmentListId);

    @Query("SELECT al FROM AppointmentRecord al JOIN al.patient p JOIN al.appointmentList l WHERE p.id = :patientId")
    List<AppointmentRecord> findByPatientId(Long patientId);
}
