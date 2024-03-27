package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Repositories.MedicineRepo;

import java.util.List;
import java.util.Objects;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;
    @Override
    public Medicine saveMedicine(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.setTenThuoc(medicineDto.getTenThuoc());
        medicine.setDeleted(false);
        medicine.setImage(medicineDto.getImage());
        medicine.setDonGia(medicineDto.getDonGia());
        medicine.setSoLuong(medicineDto.getSoLuong());
        medicine.setMaDonVi(medicineDto.getMaDonVi());
        medicineRepo.save(medicine);
        return medicine;
    }

    @Override
    public List<Medicine> fetchMedicineList() {
        return medicineRepo.findAllNotDelete();
    }

    @Override
    public Medicine showMedicineById(Long medicineId) {
        return medicineRepo.findById(medicineId).get();
    }

    @Override
    public Medicine showMedicineNotDeletedById(Long medicineId) {
        return medicineRepo.FindMedicineNotDeletedById(medicineId).get();
    }

    @Override
    public Medicine deleteMedicine(Long medicineId) {
        Medicine deletedMedicine = medicineRepo.FindMedicineNotDeletedById(medicineId).get();
        deletedMedicine.setDeleted(true);
        return medicineRepo.save(deletedMedicine);
    }

    @Override
    public Medicine updateMedicine(Long medicineId, MedicineDto medicineDto) {
        Medicine updatedMedicine = medicineRepo.FindMedicineNotDeletedById(medicineId).get();

        if(Objects.nonNull(medicineDto.getTenThuoc()) &&
        !"".equalsIgnoreCase(medicineDto.getTenThuoc())) {
            updatedMedicine.setTenThuoc(medicineDto.getTenThuoc());
        }
        if(Objects.nonNull(medicineDto.getImage()) &&
                !"".equalsIgnoreCase(medicineDto.getImage())) {
            updatedMedicine.setImage(medicineDto.getImage());
        }
        return medicineRepo.save(updatedMedicine);
    }

//    @Override
//    public Medicine showMedicineNotDeletedById(Long medicineId) {
//        return medicineRepo.showMedicineNotDeletedById(medicineId).get();
//    }
}
