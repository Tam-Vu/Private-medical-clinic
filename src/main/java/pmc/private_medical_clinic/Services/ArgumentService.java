/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.ArgumentDto;
import pmc.private_medical_clinic.Entity.Argument;

/**
 *
 * @author duyth
 */
@Service
public interface ArgumentService {
    Argument getArgument();
    Argument updateArgument(ArgumentDto argumentDto);
}

