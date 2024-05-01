package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.DrugDto;
import pmc.private_medical_clinic.Entity.Drug;
import pmc.private_medical_clinic.Entity.Unit;

import java.util.List;
import java.util.Optional;

@Service
public interface DrugService {
    List<Drug> getAllDrugs();
    Drug addDrug(DrugDto drugDto);
    Drug getDrugById(Long id);
    Drug updateDrug(Long id, DrugDto drugDto);
    Drug deleteDrug(Long id);
}
