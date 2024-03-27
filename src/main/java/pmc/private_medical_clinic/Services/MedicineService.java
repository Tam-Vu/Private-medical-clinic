package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Entity.Medicine;

import java.util.List;

@Service
public interface MedicineService {
    Medicine saveMedicine(MedicineDto medicineDto);

    List<Medicine> fetchMedicineList();

    Medicine showMedicineById(Long medicineId);

    Medicine showMedicineNotDeletedById(Long medicineId);

    Medicine deleteMedicine(Long medicineId);

    Medicine updateMedicine(Long medicineId, MedicineDto medicineDto);
}
