package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pmc.private_medical_clinic.Entity.Unit;

public interface UnitRepo extends JpaRepository<Unit, Integer> {
}
