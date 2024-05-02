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
import pmc.private_medical_clinic.Dto.PatientDto;
import pmc.private_medical_clinic.Entity.Patient;
import pmc.private_medical_clinic.Services.PatientService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ResponseBody
    @GetMapping("/")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    
      @ResponseBody
    @GetMapping("/phoneNumber/{phoneNumber}")
    public  ResponseEntity<Patient> getPatientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
     if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.ok(new Patient());
        }
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientService.createPatient(patientDto);
        return ResponseEntity.ok(patient);
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<Patient> updatePatientById(@PathVariable("id") Long id, @RequestBody PatientDto patientDto) {
        Patient patient = patientService.updatePatientById(id, patientDto);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deletePatientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientService.deletePatientById(id));
    }
}
