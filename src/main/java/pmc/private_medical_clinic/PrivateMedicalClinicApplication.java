package pmc.private_medical_clinic;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pmc.private_medical_clinic.Services.DrugUsageTriggerSerivce;

@SpringBootApplication
public class PrivateMedicalClinicApplication {
    public static  DrugUsageTriggerSerivce drugUsageTriggerSerivce;
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(PrivateMedicalClinicApplication.class, args);
        drugUsageTriggerSerivce = new DrugUsageTriggerSerivce();
        drugUsageTriggerSerivce.startTrigger();
    }
}
