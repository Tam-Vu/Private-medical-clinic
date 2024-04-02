package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pmc.private_medical_clinic.Entity.Principle;

import java.security.Principal;

public interface PrincipleRepo extends JpaRepository<Principle, Integer> {
    Principle getPrincipleByThamSoId(Integer id);

}
