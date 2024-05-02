package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.DiseaseDto;
import pmc.private_medical_clinic.Entity.Disease;

import java.util.List;

@Service
public interface DiseaseService {
    List<Disease> getAllDiseases();
    Disease getDiseaseById(Long id);
    Disease createDisease(DiseaseDto diseaseDto);
    Disease updateDiseaseById(Long id, DiseaseDto diseaseDto);
    Disease deleteDiseaseById(Long id);
}
