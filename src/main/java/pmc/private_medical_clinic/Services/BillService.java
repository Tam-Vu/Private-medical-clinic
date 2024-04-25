package pmc.private_medical_clinic.Services;

import pmc.private_medical_clinic.Dto.BillDto;
import pmc.private_medical_clinic.Entity.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBill();
    Bill createBill(BillDto billDto);
    Bill getBillById(Long Id);
    Bill updateBill(Long id, BillDto billDto);
    boolean deleteBill(Long id);

}
