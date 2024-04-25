package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.BillDto;
import pmc.private_medical_clinic.Entity.Bill;
import pmc.private_medical_clinic.Services.BillService;

import java.util.List;

@RestController
@RequestMapping("api/v1/bills")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/")
    public List<Bill> getAllBill() {
        return billService.getAllBill();
    }
    @PostMapping("/")
    public Bill createBill(@RequestBody BillDto billDto) {
        return billService.createBill(billDto);
    }
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable("id") Long id) {
        return billService.getBillById(id);
    }
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable("id") Long id, @RequestBody BillDto billDto) {
        return billService.updateBill(id, billDto);
    }
    @DeleteMapping("{id}")
    public boolean deleteBill(@PathVariable("id") Long id) {
        return billService.deleteBill(id);
    }
}
