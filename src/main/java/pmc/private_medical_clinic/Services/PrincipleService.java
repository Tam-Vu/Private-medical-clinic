package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.PrincipleDto;
import pmc.private_medical_clinic.Entity.Principle;

@Service
public interface PrincipleService {
    Principle updateDetails(PrincipleDto principleDto);
}
