package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;

import java.security.Principal;
import java.util.Optional;

@Service
public interface UserService {

    User registerUser(UserDto userDto);

    User updateInfo(UserDto userDto, Principal principal);

    User findByEmail(String email);

    User getUserByUsername(String tenDangNhap);

    User findByUsername(String tenDangNhap);

    User changePassword(UserDto userDto, Principal principal);
}
