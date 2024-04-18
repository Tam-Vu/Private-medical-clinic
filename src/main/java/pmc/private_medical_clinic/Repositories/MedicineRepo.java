package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Medicine;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {

    @Query("select m from Medicine m where m.isDeleted = false")
    List<Medicine> findAllNotDelete();
    @Query("select m from Medicine m where m.isDeleted = false and m.thuocId = :medicineId")
    Optional<Medicine> FindMedicineNotDeletedById(@Param("medicineId") Long medicineId);

    @Query("update Medicine m set m.isDeleted = true WHERE m.thuocId = :medicineId")
    Medicine fakeDeleteById(@Param("medicineId") Long medicineId);
}


