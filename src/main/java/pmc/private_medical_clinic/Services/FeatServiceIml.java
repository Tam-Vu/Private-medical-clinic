/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Feat;
import pmc.private_medical_clinic.Repositories.FeatRepo;

/**
 *
 * @author duyth
 */
@Service
public class FeatServiceIml implements FeatService {

    @Autowired
    private FeatRepo featRepo;

    @Override
    public List<Feat> getAllFeats() {
        return featRepo.findAll();
    }

}
