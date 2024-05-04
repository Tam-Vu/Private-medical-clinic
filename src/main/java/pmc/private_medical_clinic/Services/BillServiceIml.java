package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.BillDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Entity.Bill;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Repositories.BillRepo;

import java.util.List;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;
import pmc.private_medical_clinic.Repositories.AppointmentListPatientRepo;

@Service
public class BillServiceIml implements BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    AppointmentListPatientRepo appointmentListPatientRepo;

    @Override
    public List<Bill> getAllBill() {
        return (List<Bill>) billRepo.findAllBill();
    }

    @Override
    public Bill createBill(BillDto billDto) {
        Bill bill = new Bill();
        Patient patient = new Patient();
        patient.setId(billDto.getPatientId());
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.setId(billDto.getAppointmentListId());
        bill.setPatient(patient);
        bill.setAppointmentList(appointmentList);
        bill.setTotalCost(billDto.getTotalCost());
        billRepo.save(bill);
        return bill;
    }

    @Override
    public Bill getBillById(Long Id) {
        return billRepo.findById(Id).orElse(null);
    }

    @Override
    public Bill updateBill(Long id, BillDto billDto) {
        Bill bill = billRepo.findById(id).orElse(null);
        if (bill == null) {
            return null;
        } else {
            Patient patient = new Patient();
            patient.setId(billDto.getPatientId());
            AppointmentList appointmentList = new AppointmentList();
            appointmentList.setId(billDto.getAppointmentListId());
            bill.setPatient(patient);
            bill.setAppointmentList(appointmentList);
            bill.setTotalCost(billDto.getTotalCost());
            billRepo.save(bill);
            return bill;
        }
    }

    @Override
    public boolean deleteBill(Long id) {
        Bill bill = billRepo.findById(id).orElse(null);
        if (bill != null) {
            billRepo.delete(bill);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Bill getBillByAppointmentListPatientId(Long appointmentListPatientId) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientRepo.findById(appointmentListPatientId).get();
        Long patientId = appointmentListPatient.getPatient().getId();
        Long appointmentListId = appointmentListPatient.getAppointmentList().getId();
        return billRepo.findBillByAppointmentListPatientId(patientId, appointmentListId);
    }
}
