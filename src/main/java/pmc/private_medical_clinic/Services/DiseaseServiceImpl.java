package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.DiseaseDto;
import pmc.private_medical_clinic.Entity.Disease;
import pmc.private_medical_clinic.Repositories.DiseaseRepo;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    private final DiseaseRepo diseaseRepo;

    public DiseaseServiceImpl(DiseaseRepo diseaseRepo) {
        this.diseaseRepo = diseaseRepo;
    }

    @Override
    public List<Disease> getAllDiseases() {
        return diseaseRepo.findAll();
    }

    @Override
    public Disease getDiseaseById(Long id) {
        return diseaseRepo.findById(id).get();
    }

    @Override
    public Disease createDisease(DiseaseDto diseaseDto) {
        Disease diseaseExists = diseaseRepo.findDiseaseByDiseaseName(diseaseDto.getDiseaseName());
        if (diseaseExists != null) {
            return diseaseExists;
        }
        Disease disease = new Disease();
        disease.setDiseaseName(diseaseDto.getDiseaseName());
        diseaseRepo.save(disease);
        return disease;
    }

    @Override
    public Disease updateDiseaseById(Long id, DiseaseDto diseaseDto) {
        Disease disease = diseaseRepo.findById(id).get();
        Disease diseaseExists = diseaseRepo.findDiseaseByDiseaseName(diseaseDto.getDiseaseName());
        if (disease != null && diseaseExists == null) {
            disease.setDiseaseName(diseaseDto.getDiseaseName());
            diseaseRepo.save(disease);
        }
        return disease;
    }

    @Override
    public Disease deleteDiseaseById(Long id) {
        Disease disease = diseaseRepo.findById(id).get();
        if (disease != null)
            diseaseRepo.deleteById(id);
        return disease;
    }
}
