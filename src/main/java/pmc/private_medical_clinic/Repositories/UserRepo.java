package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    User findByTenDangNhap(String tenDangNhap);

    Optional<User> findByTenDangNhapOrEmail(String tenDangNhap, String email);
    Optional<User> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    Optional<User> findByEmailAndMatKhau(String email, String matKhau);
}
