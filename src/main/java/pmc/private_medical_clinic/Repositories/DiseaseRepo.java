package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Disease;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Long> {
    @Query("select m from Disease m where m.diseaseName = :diseaseName")
    Disease findDiseaseByDiseaseName(String diseaseName);
}
