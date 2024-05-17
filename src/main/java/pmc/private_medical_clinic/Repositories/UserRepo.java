package pmc.private_medical_clinic.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import pmc.private_medical_clinic.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String Username);

    User getUserByUsername(String Username);

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.email = :email")
    void updatePassword(@PathVariable("email") String email, @PathVariable("password") String password);

    @Query("Select u from User u  where u.email = :email")
    User findUserByEmail(String email);
    
        @Query("Select u from User u  where u.username = :username")
    User findUserByUserName(String username);
}
