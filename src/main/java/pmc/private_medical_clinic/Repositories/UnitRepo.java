package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Unit;
@Repository
public interface UnitRepo extends JpaRepository<Unit, Integer> {
}
