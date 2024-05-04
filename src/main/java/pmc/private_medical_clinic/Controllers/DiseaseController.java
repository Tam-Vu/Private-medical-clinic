package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.DiseaseDto;
import pmc.private_medical_clinic.Entity.Disease;
import pmc.private_medical_clinic.Services.DiseaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diseases")
@CrossOrigin(origins = "*")
public class DiseaseController {
    @Autowired
    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }
    @ResponseBody
    @GetMapping("/")
    public List<Disease> getAllDiseases() {
        return diseaseService.getAllDiseases();
    }
    @ResponseBody
    @GetMapping("detail/{id}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable Long id) {
        Disease disease = diseaseService.getDiseaseById(id);
        if (disease == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(disease);
    }
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Disease> createDisease(@RequestBody DiseaseDto diseaseDto) {
        Disease disease = diseaseService.createDisease(diseaseDto);
        if (disease != null)
            return ResponseEntity.ok(disease);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @PutMapping("update/{id}")
    public ResponseEntity<Disease> updateDiseaseById(@PathVariable Long id, @RequestBody DiseaseDto diseaseDto) {
        Disease disease = diseaseService.updateDiseaseById(id, diseaseDto);
        if (disease != null)
            return ResponseEntity.ok(disease);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Disease> deleteDiseaseById(@PathVariable Long id) {
        Disease disease = diseaseService.deleteDiseaseById(id);
        if (disease != null)
            return ResponseEntity.ok(disease);
        else
            return ResponseEntity.notFound().build();
    }
}
