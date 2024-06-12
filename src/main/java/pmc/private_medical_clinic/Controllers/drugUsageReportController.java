/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Entity.DrugUsageReport;
import pmc.private_medical_clinic.Services.DrugUsageReportService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/drugusagereports")
@CrossOrigin(origins = "*")
public class drugUsageReportController {

    @Autowired
    private DrugUsageReportService drugUsageReportService;

    @ResponseBody
    @GetMapping("/")
    public List<DrugUsageReport> getAllDrugUsageReport() {
        return drugUsageReportService.getAllDrugUsageReport();
    }
}
