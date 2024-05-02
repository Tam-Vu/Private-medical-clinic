package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Entity.AppointmentRecord;
import pmc.private_medical_clinic.Entity.Disease;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.AppointmentRecordRepo;

import java.util.List;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;
import pmc.private_medical_clinic.Entity.Bill;
import pmc.private_medical_clinic.Repositories.AppointmentListPatientRepo;
import pmc.private_medical_clinic.Repositories.BillRepo;

@Service
public class AppointmentRecordServiceImpl implements AppointmentRecordService {

    @Autowired
    private final AppointmentRecordRepo appointmentRecordRepo;

    @Autowired
    AppointmentListPatientRepo appointmentListPatientRepo;

    @Autowired
    BillRepo billRepo;

    public AppointmentRecordServiceImpl(AppointmentRecordRepo appointmentRecordRepo) {
        this.appointmentRecordRepo = appointmentRecordRepo;
    }

    @Override
    public List<AppointmentRecord> getAllAppointmentRecords() {
        return appointmentRecordRepo.findAll();
    }

    @Override
    public AppointmentRecord createAppointmentRecord(AppointmentRecordDto appointmentRecordDto) {
        AppointmentRecord appointmentRecord = new AppointmentRecord();
        appointmentRecord.setSymptoms(appointmentRecordDto.getSymptoms());
        Patient patient = new Patient();
        patient.setId(appointmentRecordDto.getPatientId());
        appointmentRecord.setPatient(patient);
        Disease disease = new Disease();
        disease.setId(appointmentRecordDto.getDiseaseId());
        appointmentRecord.setDisease(disease);
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setId(appointmentRecordDto.getAppointmentListId());
        appointmentRecord.setAppointmentList(appointmentList);
        appointmentRecordRepo.save(appointmentRecord);
        return appointmentRecord;
    }

    @Override
    public AppointmentRecord getAppointmentRecordById(Long id) {
        return appointmentRecordRepo.findById(id).get();
    }

    @Override
    public AppointmentRecord updateAppointmentRecordById(Long id, AppointmentRecordDto appointmentRecordDto) {
        AppointmentRecord appointmentRecord = appointmentRecordRepo.findById(id).get();
        if (appointmentRecord != null) {
            appointmentRecord.setSymptoms(appointmentRecordDto.getSymptoms());
            Patient patient = new Patient();
            patient.setId(appointmentRecordDto.getPatientId());
            appointmentRecord.setPatient(patient);
            Disease disease = new Disease();
            disease.setId(appointmentRecordDto.getDiseaseId());
            appointmentRecord.setDisease(disease);
            AppointmentList appointmentList = new AppointmentList();
            appointmentList.setId(appointmentRecordDto.getAppointmentListId());
            appointmentRecord.setAppointmentList(appointmentList);
            appointmentRecordRepo.save(appointmentRecord);
        }
        return appointmentRecord;
    }

    @Override
    public AppointmentRecord deleteAppointmentRecordById(Long id) {
        AppointmentRecord appointmentRecord = appointmentRecordRepo.findById(id).get();
        if (appointmentRecord != null) {
            appointmentRecordRepo.deleteById(id);
        }
        return appointmentRecord;
    }

    @Override
    public AppointmentRecord getRecordByAppointmentListPatientId(Long appointmentListPatientId) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientRepo.findById(appointmentListPatientId).get();
        Long patientId = appointmentListPatient.getPatient().getId();
        Long appointmentListId = appointmentListPatient.getAppointmentList().getId();
        return appointmentRecordRepo.findRecordByAppointmentListPatientId(patientId, appointmentListId);
    }

    @Override
    public AppointmentRecord getRecordByBillId(Long billId) {
        Bill bill = billRepo.findById(billId).get();
        Long patientId = bill.getPatient().getId();
        Long appointmentListId = bill.getAppointmentList().getId();
        return appointmentRecordRepo.findRecordByAppointmentListPatientId(patientId, appointmentListId);
    }

    @Override
    public List<AppointmentRecord> getByPatientId(Long patientId) {
        return (List<AppointmentRecord>) appointmentRecordRepo.findByPatientId(patientId);
    }
}
