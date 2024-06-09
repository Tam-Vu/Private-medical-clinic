/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Dto.AppointmentListPatientDto;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;
import pmc.private_medical_clinic.Repositories.AppointmentListPatientRepo;
import pmc.private_medical_clinic.Services.AppointmentListPatientService;
import pmc.private_medical_clinic.Services.AppointmentListService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/appointmentlistpatients")
@CrossOrigin(origins = "*")
public class AppointmentListPatientController {

    @Autowired
    private AppointmentListPatientService appointmentListPatientService;

    @ResponseBody
    @GetMapping("/")
    public List<AppointmentListPatient> getAllAppointmentList() {
        return appointmentListPatientService.getAllAppointmentList();
    }


    @ResponseBody
    @GetMapping("/appointmentList/{appointmentListId}")
    public List<AppointmentListPatient> getByAppointmentListId(@PathVariable("appointmentListId") Long appointmentListId) {
        return appointmentListPatientService.getByAppointmentListId(appointmentListId);
    }

    @ResponseBody
    @GetMapping("/patientId/{patientId}")
    public List<AppointmentListPatient> getByPatientId(@PathVariable("patientId") Long patientId) {
        return appointmentListPatientService.getByPatientId(patientId);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<AppointmentListPatient> createAppointmentListPatient(@RequestBody AppointmentListPatientDto appointmentListPatientDto) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientService.createAppointmentListPatient(appointmentListPatientDto);
        return ResponseEntity.ok(appointmentListPatient);
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<AppointmentListPatient> updatePatientById(@PathVariable("id") Long id, @RequestBody AppointmentListPatientDto appointmentListPatientDto) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientService.updateAppointmentListPatient(id, appointmentListPatientDto);
        if (appointmentListPatient != null) {
            return ResponseEntity.ok(appointmentListPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAppointmentListPatient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentListPatientService.deleteAppointmentListPatient(id));
    }

    @ResponseBody
    @GetMapping(value = "/{appointmentListPatientId}")
    public ResponseEntity<AppointmentListPatient> getAppointmentListPatientById(@PathVariable("appointmentListPatientId") Long id) {
        return ResponseEntity.ok(appointmentListPatientService.getAppointmentListPatientById(id));
    }
    
        @ResponseBody
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<AppointmentListPatient> moveAppointmentListPatientToTheEnd(@PathVariable("id") Long id) {
        AppointmentListPatient appointmentListPatient = appointmentListPatientService.moveAppointmentListPatientToTheEnd(id);
        if (appointmentListPatient != null) {
            return ResponseEntity.ok(appointmentListPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
