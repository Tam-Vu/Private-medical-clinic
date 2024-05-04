package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.AppointmentListRecordDetail;

import java.util.List;

@Repository
public interface AppointmentListRecordDetailRepo extends JpaRepository<AppointmentListRecordDetail, Long> {

    @Query("SELECT m FROM AppointmentListRecordDetail m JOIN m.appointmentListRecord JOIN m.drug JOIN m.usage")
    List<AppointmentListRecordDetail> getAllAppointmentListRecordDetail();

    @Query("SELECT m FROM AppointmentListRecordDetail m JOIN m.appointmentListRecord JOIN m.drug JOIN m.usage WHERE m.appointmentListRecord.id = :appointmentRecordId")
    List<AppointmentListRecordDetail> findDetailByAppointmentRecordId(Long appointmentRecordId);
}
