package pmc.private_medical_clinic.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.AppointmentListRecordDetailDto;
import pmc.private_medical_clinic.Entity.AppointmentListRecordDetail;
import pmc.private_medical_clinic.Services.AppointmentListRecordDetailService;

import java.util.List;

@RestController
@RequestMapping("api/v1/appointmentlistrecorddetail")
public class AppointmentListRecorDetailController {
    @Autowired
    private AppointmentListRecordDetailService appointmentListRecordDetailService;
    @GetMapping("/")
    public List<AppointmentListRecordDetail> getAllAppointmentListRecordDetail() {
        return appointmentListRecordDetailService.getAllAppointmentListRecordDetail();
    }
    @PostMapping("/")
    public AppointmentListRecordDetail createAppointmentListRecordDetail(@RequestBody AppointmentListRecordDetailDto appointmentListRecordDetailDto) {
        return appointmentListRecordDetailService.createAppointmentListRecordDetail(appointmentListRecordDetailDto);
    }
    @GetMapping("/{id}")
    public AppointmentListRecordDetail getAppointmentListRecordDetailById(@PathVariable("id") Long id) {
        return appointmentListRecordDetailService.getAppointmentListRecordDetailById(id);
    }
    @PutMapping("/{id}")
    public AppointmentListRecordDetail updateAppointmentListRecordDetail(@PathVariable("id") Long id, @RequestBody AppointmentListRecordDetailDto appointmentListRecordDetailDto) {
        return appointmentListRecordDetailService.updateAppointmentListRecordDetail(id, appointmentListRecordDetailDto);
    }
    @DeleteMapping("{id}")
    public boolean deleteAppointmentListRecordDetail(@PathVariable("id") Long id) {
        return appointmentListRecordDetailService.deleteAppointmentListRecordDetail(id);
    }
}
