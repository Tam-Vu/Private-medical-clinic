/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Feat;

/**
 *
 * @author duyth
 */
@Service
public interface FeatService {
    List<Feat> getAllFeats();
}
