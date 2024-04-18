package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Entity.Unit;
import pmc.private_medical_clinic.Repositories.MedicineRepo;
import pmc.private_medical_clinic.failureHandler.GlobalExceptionHandler;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;
import pmc.private_medical_clinic.utils.ImageUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;
    @Override
    public Medicine saveMedicine(MedicineDto medicineDto, Unit unit, MultipartFile file) throws IOException {
        Medicine medicine = new Medicine();
        medicine.setTenThuoc(medicineDto.getTenThuoc());
        medicine.setDeleted(false);
        if(file.isEmpty() == false) {
            medicine.setImage(file.getBytes());
        }
        medicine.setDonGia(medicineDto.getDonGia());
        if(medicineDto.getSoLuong() <= 0) {
            throw new GlobalExceptionHandler("Số lượng phải lớn hơn hoặc bằng 0");
        }
        medicine.setSoLuong(medicineDto.getSoLuong());
        medicine.setUnit(unit);
        medicineRepo.save(medicine);
        return medicine;
    }

    @Override
    public List<Medicine> fetchMedicineList() {
        return medicineRepo.findAllNotDelete();
    }

    @Override
    public Medicine showMedicineById(Long medicineId) {

         Medicine medicine = medicineRepo.findById(medicineId).get();
         return medicine;
    }

    @Override
    public Medicine showMedicineNotDeletedById(Long medicineId) {
        return medicineRepo.FindMedicineNotDeletedById(medicineId).orElseThrow(() -> new RuntimeException("Not found medicine"));
    }

    @Override
    public Medicine deleteMedicine(Long medicineId) {
        Medicine deletedMedicine = medicineRepo.FindMedicineNotDeletedById(medicineId).orElseThrow(() -> new RuntimeException("Not found medicine"));
        deletedMedicine.setDeleted(true);
        return medicineRepo.save(deletedMedicine);
    }

    @Override
    public Medicine updateMedicine(Long medicineId, MedicineDto medicineDto, MultipartFile file) throws IOException {
        Medicine updatedMedicine = medicineRepo.FindMedicineNotDeletedById(medicineId).orElseThrow(() -> new RuntimeException("Not found medicine"));

        if(Objects.nonNull(medicineDto.getTenThuoc()) &&
        !"".equalsIgnoreCase(medicineDto.getTenThuoc())) {
            updatedMedicine.setTenThuoc(medicineDto.getTenThuoc());
        }
        if(file.isEmpty() == false) {
            updatedMedicine.setImage(file.getBytes());
        }
        updatedMedicine.setSoLuong(medicineDto.getSoLuong());
        updatedMedicine.setDonGia(medicineDto.getDonGia());
        return medicineRepo.save(updatedMedicine);
    }

//    @Override
//    public Medicine showMedicineNotDeletedById(Long medicineId) {
//        return medicineRepo.showMedicineNotDeletedById(medicineId).get();
//    }
}
