package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Entity.Disease;
import pmc.private_medical_clinic.Entity.AppointmentListRecord;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.AppointmentListPatientRepo;
import pmc.private_medical_clinic.Repositories.AppointmentListRecordRepo;

import java.util.List;
@Service
public class AppointmentListRecordServiceIml implements AppointmentListRecordService{
    @Autowired
    AppointmentListRecordRepo appointmentListRecordRepo;
    @Override
    public List<AppointmentListRecord> getAllAppointmentListRecord() {
        return (List<AppointmentListRecord>) appointmentListRecordRepo.findAllAppointmentListRecord();
    }
    @Override
    public AppointmentListRecord createAppointmentListRecord(AppointmentListRecordDto appointmentListRecordDto) {
        AppointmentListRecord appointmentListRecord = new AppointmentListRecord();
        Patient patient = new Patient();
        patient.setId(appointmentListRecordDto.getPatientId());
        Disease disease = new Disease();
        disease.setId(appointmentListRecordDto.getDiseaseId());
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setId(appointmentListRecordDto.getAppointmentListId());
        appointmentListRecord.setPatient(patient);
        appointmentListRecord.setDisease(disease);
        appointmentListRecord.setSymptoms(appointmentListRecordDto.getSymptoms());
        appointmentListRecord.setAppointmentList(appointmentList);
        appointmentListRecordRepo.save(appointmentListRecord);
        return appointmentListRecord;
    }

    @Override
    public AppointmentListRecord getAppointmentListRecordById(Long id) {
        AppointmentListRecord appointmentListRecord = appointmentListRecordRepo.findById(id).orElse(null);
        return appointmentListRecord;

    }

    @Override
    public AppointmentListRecord updateAppointmentListRecord(Long id,AppointmentListRecordDto appointmentListRecordDto) {
        AppointmentListRecord  appointmentListRecord = appointmentListRecordRepo.findById(id).get();
        if(appointmentListRecord ==null){
            return null;
        }
        else{
            Patient patient = new Patient();
            patient.setId(appointmentListRecordDto.getPatientId());
            Disease disease = new Disease();
            disease.setId(appointmentListRecordDto.getDiseaseId());
            AppointmentList appointmentList = new AppointmentList();
            appointmentList.setId(appointmentListRecordDto.getAppointmentListId());
            appointmentListRecord.setPatient(patient);
            appointmentListRecord.setDisease(disease);
            appointmentListRecord.setSymptoms(appointmentListRecordDto.getSymptoms());
            appointmentListRecord.setAppointmentList(appointmentList);
            appointmentListRecordRepo.save(appointmentListRecord);
            return appointmentListRecord;
        }
    }

    @Override
    public boolean deleteAppointmentListRecord(Long id) {
        if (appointmentListRecordRepo.findById(id) != null) {
            appointmentListRecordRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
