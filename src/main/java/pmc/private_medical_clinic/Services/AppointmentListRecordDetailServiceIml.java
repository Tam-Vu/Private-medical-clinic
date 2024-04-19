package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListRecordDetailDto;
import pmc.private_medical_clinic.Entity.AppointmentListRecord;
import pmc.private_medical_clinic.Entity.AppointmentListRecordDetail;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Entity.Usage;
import pmc.private_medical_clinic.Repositories.AppointmentListRecordDetailRepo;

import java.util.List;

@Service
public class AppointmentListRecordDetailServiceIml implements AppointmentListRecordDetailService{
    @Autowired
    private AppointmentListRecordDetailRepo appointmentListRecordDetailRepo;

    @Override
    public List<AppointmentListRecordDetail> getAllAppointmentListRecordDetail() {
        return (List<AppointmentListRecordDetail> ) appointmentListRecordDetailRepo.getAllAppointmentListRecordDetail();
    }

    @Override
    public AppointmentListRecordDetail createAppointmentListRecordDetail(AppointmentListRecordDetailDto appointmentListRecordDetailDto) {
        AppointmentListRecordDetail appointmentListRecordDetail = new AppointmentListRecordDetail();
        AppointmentListRecord appointmentListRecord = new AppointmentListRecord();
        appointmentListRecord.setId(appointmentListRecordDetailDto.getAppointmentListRecordId());
        appointmentListRecordDetail.setAppointmentListRecord(appointmentListRecord);
        Medicine medicine = new Medicine();
        medicine.setThuocId(appointmentListRecordDetailDto.getMedicineId());
        appointmentListRecordDetail.setMedicine(medicine);
        Usage usage = new Usage();
        usage.setId(appointmentListRecordDetailDto.getUsageId());
        appointmentListRecordDetail.setUsage(usage);
        appointmentListRecordDetail.setQuantity(appointmentListRecordDetailDto.getQuantity());
        appointmentListRecordDetailRepo.save(appointmentListRecordDetail);
        return appointmentListRecordDetail;
    }

    @Override
    public AppointmentListRecordDetail getAppointmentListRecordDetailById(Long id) {
        AppointmentListRecordDetail appointmentListRecordDetail = appointmentListRecordDetailRepo.findById(id).orElse(null);
        return appointmentListRecordDetail;
    }

    @Override
    public AppointmentListRecordDetail updateAppointmentListRecordDetail(Long id, AppointmentListRecordDetailDto updateAppointmentListRecordDetailDto) {
        AppointmentListRecordDetail appointmentListRecordDetail = appointmentListRecordDetailRepo.findById(id).get();
        if(appointmentListRecordDetail == null){
            return null;
        }
        AppointmentListRecord appointmentListRecord = new AppointmentListRecord();
        appointmentListRecord.setId(updateAppointmentListRecordDetailDto.getAppointmentListRecordId());

        Medicine medicine = new Medicine();
        medicine.setThuocId(updateAppointmentListRecordDetailDto.getMedicineId());

        Usage usage = new Usage();
        usage.setId(updateAppointmentListRecordDetailDto.getUsageId());

        appointmentListRecordDetail.setQuantity(updateAppointmentListRecordDetailDto.getQuantity());
        appointmentListRecordDetailRepo.save(appointmentListRecordDetail);

        return appointmentListRecordDetail;
    }

    @Override
    public boolean deleteAppointmentListRecordDetail(Long id) {
        if (appointmentListRecordDetailRepo.findById(id) != null) {
            appointmentListRecordDetailRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
