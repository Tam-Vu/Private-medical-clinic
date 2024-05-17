package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListRecordDetailDto;
import pmc.private_medical_clinic.Entity.AppointmentRecord;
import pmc.private_medical_clinic.Entity.AppointmentListRecordDetail;
import pmc.private_medical_clinic.Entity.Usage;
import pmc.private_medical_clinic.Repositories.AppointmentListRecordDetailRepo;

import java.util.List;
import pmc.private_medical_clinic.Entity.Drug;
import pmc.private_medical_clinic.Repositories.DrugRepo;

@Service
public class AppointmentListRecordDetailServiceIml implements AppointmentListRecordDetailService {

    @Autowired
    private AppointmentListRecordDetailRepo appointmentListRecordDetailRepo;

    @Autowired
    private DrugRepo drugRepo;

    @Override
    public List<AppointmentListRecordDetail> getAllAppointmentListRecordDetail() {
        return (List<AppointmentListRecordDetail>) appointmentListRecordDetailRepo.getAllAppointmentListRecordDetail();
    }

    @Override
    public AppointmentListRecordDetail createAppointmentListRecordDetail(AppointmentListRecordDetailDto appointmentListRecordDetailDto) {
        AppointmentListRecordDetail appointmentListRecordDetail = new AppointmentListRecordDetail();
        AppointmentRecord appointmentListRecord = new AppointmentRecord();
        appointmentListRecord.setId(appointmentListRecordDetailDto.getAppointmentRecordId());
        appointmentListRecordDetail.setAppointmentListRecord(appointmentListRecord);
        Drug drug = drugRepo.findDrugdById(appointmentListRecordDetailDto.getDrugId());
        appointmentListRecordDetail.setDrug(drug);
        Usage usage = new Usage();
        usage.setId(appointmentListRecordDetailDto.getUsageId());
        appointmentListRecordDetail.setUsage(usage);
        appointmentListRecordDetail.setCount(appointmentListRecordDetailDto.getCount());
        appointmentListRecordDetail.setDrugPrice(appointmentListRecordDetailDto.getDrugPrice());
        appointmentListRecordDetailRepo.save(appointmentListRecordDetail);
        drug.setCount(drug.getCount() - appointmentListRecordDetailDto.getCount());
        drugRepo.save(drug);
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
        if (appointmentListRecordDetail == null) {
            return null;
        }
        AppointmentRecord appointmentListRecord = new AppointmentRecord();
        appointmentListRecord.setId(updateAppointmentListRecordDetailDto.getAppointmentRecordId());
        appointmentListRecordDetail.setAppointmentListRecord(appointmentListRecord);

        Drug drug = new Drug();
        drug.setId(updateAppointmentListRecordDetailDto.getDrugId());
        appointmentListRecordDetail.setDrug(drug);

        Usage usage = new Usage();
        usage.setId(updateAppointmentListRecordDetailDto.getUsageId());
        appointmentListRecordDetail.setUsage(usage);

        appointmentListRecordDetail.setCount(updateAppointmentListRecordDetailDto.getCount());
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

    @Override
    public List<AppointmentListRecordDetail> getDetailByAppointmentRecordId(Long appointmentRecordId) {
        return appointmentListRecordDetailRepo.findDetailByAppointmentRecordId(appointmentRecordId);
    }
}
