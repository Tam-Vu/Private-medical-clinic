package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.UsageDto;
import pmc.private_medical_clinic.Entity.Usage;
import pmc.private_medical_clinic.Services.UsageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usages")
@CrossOrigin(origins = "*")
public class UsageController {
    @Autowired
    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }
    @ResponseBody
    @GetMapping("/")
    public List<Usage> getAllUsages() {
        return usageService.getAllUsages();
    }
    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Usage> createUsage(@RequestBody UsageDto usageDto) {
        return ResponseEntity.ok(usageService.createUsage(usageDto));
    }
    @ResponseBody
    @PutMapping("update/{id}")
    public ResponseEntity<Usage> updateUsageById(@PathVariable Long id, @RequestBody UsageDto usageDto) {
        Usage usage = usageService.updateUsageById(id, usageDto);
        if (usage == null) {
            return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.ok(usage);
    }
    @ResponseBody
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Usage> deleteUsageById(@PathVariable Long id) {
        Usage usage = usageService.deleteUsageById(id);
        if (usage == null) {
            return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.ok(usage);
    }
}
