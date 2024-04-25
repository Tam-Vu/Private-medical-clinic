package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.AppointmentListRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentListRecord;
import pmc.private_medical_clinic.Services.AppointmentListRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointmentlistrecords")
@CrossOrigin

public class AppointmentListRecordController {
    @Autowired
    private AppointmentListRecordService appointmentListRecordService;

    @ResponseBody
    @GetMapping("/")
    public List<AppointmentListRecord> getAllAppointmentListRecord() {
        return appointmentListRecordService.getAllAppointmentListRecord();
    }

    @ResponseBody
    @PostMapping("/")
    public AppointmentListRecord createAppointmentListRecord(@RequestBody AppointmentListRecordDto appointmentListRecordDto) {
        return appointmentListRecordService.createAppointmentListRecord(appointmentListRecordDto);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public AppointmentListRecord getAppointmentListRecordById(@PathVariable("id") Long id) {
        return appointmentListRecordService.getAppointmentListRecordById(id);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public AppointmentListRecord updateAppointmentListRecord(@PathVariable("id") Long id, @RequestBody AppointmentListRecordDto appointmentListRecordDto) {
        return appointmentListRecordService.updateAppointmentListRecord(id, appointmentListRecordDto);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public boolean deleteAppointmentListRecord(@PathVariable("id") Long id) {
        return appointmentListRecordService.deleteAppointmentListRecord(id);
    }
}
