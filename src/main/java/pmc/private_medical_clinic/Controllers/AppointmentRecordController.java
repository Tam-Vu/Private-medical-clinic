package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.AppointmentRecordDto;
import pmc.private_medical_clinic.Entity.AppointmentRecord;
import pmc.private_medical_clinic.Services.AppointmentRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointmentrecords")
@CrossOrigin(origins = "*")
public class AppointmentRecordController {

    @Autowired
    private final AppointmentRecordService appointmentRecordService;

    public AppointmentRecordController(AppointmentRecordService appointmentRecordService) {
        this.appointmentRecordService = appointmentRecordService;
    }

    @ResponseBody
    @GetMapping("/")
    public List<AppointmentRecord> getAllAppointmentRecords() {
        return appointmentRecordService.getAllAppointmentRecords();
    }

    @ResponseBody
    @GetMapping("detail/{id}")
    public ResponseEntity<AppointmentRecord> getAppointmentRecordById(@PathVariable Long id) {
        AppointmentRecord appointmentRecord = appointmentRecordService.getAppointmentRecordById(id);
        if (appointmentRecord != null) {
            return ResponseEntity.ok(appointmentRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<AppointmentRecord> createAppointmentRecord(@RequestBody AppointmentRecordDto appointmentRecordDto) {
        AppointmentRecord appointmentRecord = appointmentRecordService.createAppointmentRecord(appointmentRecordDto);
        return ResponseEntity.ok(appointmentRecord);

    }

    @ResponseBody
    @PutMapping("update/{id}")
    public ResponseEntity<AppointmentRecord> updateAppointmentRecordById(@PathVariable Long id, @RequestBody AppointmentRecordDto appointmentRecordDto) {
        AppointmentRecord appointmentRecord = appointmentRecordService.updateAppointmentRecordById(id, appointmentRecordDto);
        if (appointmentRecord != null) {
            return ResponseEntity.ok(appointmentRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @DeleteMapping("delete/{id}")
    public ResponseEntity<AppointmentRecord> deleteAppointmentRecordById(@PathVariable Long id) {
        AppointmentRecord appointmentRecord = appointmentRecordService.deleteAppointmentRecordById(id);
        if (appointmentRecord != null) {
            return ResponseEntity.ok(appointmentRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/appointmentListPatientId/{appointmentListPatientId}")
    public ResponseEntity<AppointmentRecord> getRecordByAppointmentListPatientId(@PathVariable("appointmentListPatientId") Long appointmentListPatientId) {
        AppointmentRecord appointmentRecord = appointmentRecordService.getRecordByAppointmentListPatientId(appointmentListPatientId);
        if (appointmentRecord != null) {
            return ResponseEntity.ok(appointmentRecord);
        } else {
            return ResponseEntity.ok(new AppointmentRecord());
        }
    }

    @ResponseBody
    @GetMapping("/bill/{billId}")
    public ResponseEntity<AppointmentRecord> getRecordByBillId(@PathVariable("billId") Long billId) {
        AppointmentRecord appointmentRecord = appointmentRecordService.getRecordByBillId(billId);
        if (appointmentRecord != null) {
            return ResponseEntity.ok(appointmentRecord);
        } else {
            return ResponseEntity.ok(new AppointmentRecord());
        }
    }

    @ResponseBody
    @GetMapping("/patientId/{patientId}")
    public List<AppointmentRecord> getByPatientId(@PathVariable("patientId") Long patientId) {
        return appointmentRecordService.getByPatientId(patientId);
    }
}
