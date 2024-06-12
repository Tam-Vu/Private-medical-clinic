package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Drug;

@Repository
public interface DrugRepo extends JpaRepository<Drug, Long> {

    @Query("select m from Drug m where m.drugName = :drugName")
    Drug findDrugByDrugName(String drugName);

    @Query("select m from Drug m where m.id = :drugId")
    Drug findDrugdById (Long drugId);
}
