package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.DrugDto;
import pmc.private_medical_clinic.Entity.Drug;
import pmc.private_medical_clinic.Services.DrugService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drugs")
@CrossOrigin(origins = "*")
public class DrugController {
    @Autowired
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @ResponseBody
    @GetMapping("/")
    public List<Drug> getAllDrugs() {
        return drugService.getAllDrugs();
    }
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Drug> addDrug(@RequestBody DrugDto drugDto) {
        Drug drug = drugService.addDrug(drugDto);
        return ResponseEntity.ok(drug);
    }
    @ResponseBody
    @GetMapping("detail/{id}")
    public ResponseEntity<Drug> getDrugById(@PathVariable Long id) {
        Drug drug = drugService.getDrugById(id);
        if (drug != null)
            return ResponseEntity.ok(drug);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @PutMapping("update/{id}")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long id, @RequestBody DrugDto drugDto) {
        Drug drug = drugService.updateDrug(id, drugDto);
        if (drug != null)
            return ResponseEntity.ok(drug);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @PutMapping("deactivate/{id}")
    public ResponseEntity<Drug> deactivateDrug(@PathVariable Long id) {
        Drug drug = drugService.deactivateDrug(id);
        if (drug != null)
            return ResponseEntity.ok(drug);
        else
            return ResponseEntity.notFound().build();
    }
}
