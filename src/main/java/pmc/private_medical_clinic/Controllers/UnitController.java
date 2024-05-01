package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Entity.Unit;
import pmc.private_medical_clinic.Services.UnitService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
@CrossOrigin(origins = "*")
public class UnitController {
    @Autowired
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }
    @ResponseBody
    @GetMapping("/")
    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Unit> createUnit(@RequestBody UnitDto unitDto) {
        Unit unit = unitService.createUnit(unitDto);
        if (unit != null)
            return ResponseEntity.ok(unit);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @PutMapping("/update/{id}")
    public ResponseEntity<Unit> updateUnitById(@PathVariable Long id,@RequestBody UnitDto unitDto) {
        Unit unit = unitService.updateUnitById(id, unitDto);
        if (unit != null)
            return ResponseEntity.ok(unit);
        else
            return ResponseEntity.notFound().build();
    }
    @ResponseBody
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Unit> deleteUnitById(@PathVariable Long id) {
        Unit unit = unitService.deleteUnitById(id);
        if (unit != null)
            return ResponseEntity.ok(unit);
        else
            return ResponseEntity.notFound().build();
    }
}
