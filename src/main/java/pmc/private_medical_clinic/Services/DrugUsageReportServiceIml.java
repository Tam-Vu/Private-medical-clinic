/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.DrugUsageReport;
import pmc.private_medical_clinic.Repositories.DrugUsageReportRepo;
import pmc.private_medical_clinic.Repositories.DrugRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import pmc.private_medical_clinic.Entity.Drug;

/**
 *
 * @author duyth
 */
@Service
public class DrugUsageReportServiceIml implements DrugUsageReportService, Job {

    @Autowired
    private final DrugUsageReportRepo drugUsageReportRepo;

    @Autowired
    private final DrugRepo drugRepo;

    public DrugUsageReportServiceIml(DrugUsageReportRepo drugUsageReportRepo, DrugRepo drugRepo) {
        this.drugUsageReportRepo = drugUsageReportRepo;
        this.drugRepo = drugRepo;
    }

    @Override
    public List<DrugUsageReport> getAllDrugUsageReport() {
        return drugUsageReportRepo.findAll();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        LocalDate today = LocalDate.now();

        // Check if it's the end of the month
        if (today.getDayOfMonth() == today.lengthOfMonth()) {
            List<Drug> listDrugs = drugRepo.findAll();
            for (Drug drug : listDrugs) {
                DrugUsageReport drugUsageReport = new DrugUsageReport();
                drugUsageReport.setCount(drug.getCount());
                drugUsageReport.setDrugId(drug.getId());
                drugUsageReport.setMonth(today.getMonthValue());
                drugUsageReport.setYear(today.getYear());
                drugUsageReportRepo.save(drugUsageReport);
                System.out.println("It's the end of the month! Day of month inside: " + today.getMonthValue());
            }
        } else {
            System.out.println("Not the end of the month yet.");
        }
    }
}
