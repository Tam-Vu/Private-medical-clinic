package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.ForgotPassword;
import pmc.private_medical_clinic.Entity.User;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword,Integer> {
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
