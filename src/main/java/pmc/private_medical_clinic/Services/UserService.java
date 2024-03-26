package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;

import java.util.Optional;

@Service
public interface UserService {

    User registerUser(UserDto userDto);

    User findByEmail(String email);

    User findByTenDangNhap(String tenDangNhap);

    public Boolean checkByEmail(String email);

    boolean checkByTenDangNhap(String tenDangNhap);

    boolean checkPassword(String email, String matKhau);

//    public Boolean checkLogin(String tenDangNhapOrEmail, String matKhau) {
//        Optional<User> userOptionalByUsername = UserRepo.findByTenDangNhapAndMatKhau(tenDangNhapOrEmail, matKhau);
//        Optional<User> userOptionalByEmail = UserRepo.findByEmailAndMatKhau(tenDangNhapOrEmail, matKhau);
//        return userOptionalByUsername.isPresent() || userOptionalByEmail.isPresent();
//    }
}
