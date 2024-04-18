package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Entity.Unit;

import java.io.IOException;
import java.util.List;

@Service
public interface MedicineService {
    Medicine saveMedicine(MedicineDto medicineDto, Unit unit, MultipartFile file) throws IOException;

    List<Medicine> fetchMedicineList();

    Medicine showMedicineById(Long medicineId);

    Medicine showMedicineNotDeletedById(Long medicineId);

    Medicine deleteMedicine(Long medicineId);

    Medicine updateMedicine(Long medicineId, MedicineDto medicineDto, MultipartFile file) throws IOException;
}
