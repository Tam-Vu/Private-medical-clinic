/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
    
    Bill getBillByAppointmentListPatientId(Long appointmentListPatientId);

}
