package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.DrugDto;
import pmc.private_medical_clinic.Entity.Drug;
import pmc.private_medical_clinic.Entity.Unit;
import pmc.private_medical_clinic.Repositories.DrugRepo;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private final DrugRepo drugRepo;

    public DrugServiceImpl(DrugRepo drugRepo) {
        this.drugRepo = drugRepo;
    }

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepo.findAll();
    }

    @Override
    public Drug addDrug(DrugDto drugDto) {
        Drug drugExists = drugRepo.findDrugByDrugName(drugDto.getDrugName());
        if (drugExists != null) {
            return drugExists;
        }
        Drug drug = new Drug();
        drug.setDrugName(drugDto.getDrugName());
        drug.setCount(drugDto.getCount());
        drug.setNote(drugDto.getNote());
        drug.setPrice(drugDto.getPrice());
        Unit unit = new Unit();
        unit.setId(drugDto.getUnitId());
        drug.setUnit(unit);
        return drugRepo.save(drug);
    }

    @Override
    public Drug getDrugById(Long id) {
        return drugRepo.findById(id).get();
    }

    @Override
    public Drug updateDrug(Long id, DrugDto drugDto) {
        Drug drug = drugRepo.findById(id).get();
        if (drug != null) {
            Unit unit = new Unit();
            unit.setId(drugDto.getUnitId());
            drug.setUnit(unit);
            drug.setDrugName(drugDto.getDrugName());
            drug.setCount(drugDto.getCount());
            drug.setNote(drugDto.getNote());
            drug.setPrice(drugDto.getPrice());
            return drugRepo.save(drug);
        }
        return drug;
    }

    @Override
    public Drug deactivateDrug(Long id) {
        Drug drug = drugRepo.findById(id).get();
        if (drug != null) {
            drug.setActive(!drug.isActive());
            return drugRepo.save(drug);
        }
        return null;
    }
}
