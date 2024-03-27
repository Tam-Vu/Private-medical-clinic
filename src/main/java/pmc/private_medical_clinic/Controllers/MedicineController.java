package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Repositories.MedicineRepo;
import pmc.private_medical_clinic.Services.MedicineService;

import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/")
    @ResponseBody
    public List<Medicine> showMedicineList() {
        return medicineService.fetchMedicineList();
    }

    @ResponseBody
    @GetMapping("detail/{id}")
    public Medicine showMedicineById(@PathVariable("id") Long medicineId) {
        return medicineService.showMedicineNotDeletedById(medicineId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMedicine(@RequestBody MedicineDto medicineDto) {
        Medicine medicine = medicineService.saveMedicine(medicineDto);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PutMapping("delete/{id}")
    public Medicine deletedMedicine(@PathVariable("id") Long medicineId) {
        return medicineService.deleteMedicine(medicineId);
    }

    @ResponseBody
    @PutMapping("update/{id}")
    public Medicine updateMedicine(@PathVariable("id") Long medicineId,
                                   @RequestBody MedicineDto medicineDto) {
        return medicineService.updateMedicine(medicineId, medicineDto);
    }

}
