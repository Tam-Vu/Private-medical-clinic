package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.RefreshToken;

import java.util.Optional;

@Repository

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

