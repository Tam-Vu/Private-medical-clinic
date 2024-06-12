/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

/**
 *
 * @author duyth
 */
public class DrugUsageTriggerSerivce {

    private JobDetail jobDetail;
    private Trigger trigger;
    private Scheduler scheduler;

    public DrugUsageTriggerSerivce() {
        System.out.println("this is me");
        // Define the job class
        jobDetail = JobBuilder.newJob(DrugUsageReportServiceIml.class)
                .withIdentity("DrugUsageReportServiceIml")
                .build();

        // Define the cron trigger to fire at midnight on the last day of the month
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("DrugUsageTriggerSerivce")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 L * ?")) // Fire at midnight (0 0 0) on the last day (L) of every month (*)
                .build();


        // Get a scheduler instance
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException ex) {
            Logger.getLogger(DrugUsageTriggerSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Schedule the job with the trigger
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(DrugUsageTriggerSerivce.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void startTrigger() throws SchedulerException {
        scheduler.start();
    }
}
