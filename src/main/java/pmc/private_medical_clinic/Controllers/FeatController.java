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
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Entity.Feat;
import pmc.private_medical_clinic.Services.FeatService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/feature")
@CrossOrigin(origins = "*")
public class FeatController {
     @Autowired
    private FeatService featService;

    @GetMapping("/")
    public List<Feat> getAllFeats() {
        return featService.getAllFeats();
    }
}
