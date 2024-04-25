package pmc.private_medical_clinic.Services;

import pmc.private_medical_clinic.Dto.AppointmentListRecordDetailDto;
import pmc.private_medical_clinic.Entity.AppointmentListRecordDetail;

import java.util.List;

public interface AppointmentListRecordDetailService {
    List<AppointmentListRecordDetail> getAllAppointmentListRecordDetail();
    AppointmentListRecordDetail createAppointmentListRecordDetail(AppointmentListRecordDetailDto appointmentListRecordDetailDto);
    AppointmentListRecordDetail getAppointmentListRecordDetailById(Long id);
    AppointmentListRecordDetail updateAppointmentListRecordDetail(Long id, AppointmentListRecordDetailDto appointmentListRecordDetailDto);
    boolean deleteAppointmentListRecordDetail(Long id);

}
