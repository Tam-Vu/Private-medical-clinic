/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Dto.AppointmentListDto;
import pmc.private_medical_clinic.Entity.AppointmentList;
import pmc.private_medical_clinic.Services.AppointmentListService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/appointmentlists")
@CrossOrigin(origins = "*")
public class AppointmentListController {

    @Autowired
    private AppointmentListService appointmentListService;

    @ResponseBody
    @GetMapping("/")
    public List<AppointmentList> getAllAppointmentList() {
        return appointmentListService.getAllAppointmentList();
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<AppointmentList> createAppointmentList(@RequestBody AppointmentListDto appointmentListDto) {
        AppointmentList appointmentList = appointmentListService.createAppointmentList(appointmentListDto);
        return ResponseEntity.ok(appointmentList);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentList> getAppointmentListById(@PathVariable("id") Long id) {
        AppointmentList appointmentList = appointmentListService.getAppointmentListById(id);
        return ResponseEntity.ok(appointmentList);
    }

}
