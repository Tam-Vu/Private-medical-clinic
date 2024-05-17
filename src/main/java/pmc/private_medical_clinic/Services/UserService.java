package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.User;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService {

    User registerUser(UserDto userDto);

    User updateInfo(UserDto userDto, Principal principal);

    User findByEmail(String email);

    User getUserByUsername(String tenDangNhap);

    User findByUsername(String tenDangNhap);

    User changePassword(UserDto userDto, Principal principal);

    List<User> getAllUsers();

    User updateUserById(Long id, UserDto userDto);

    User getUserById(Long id);
    
    User deactivateUserById(Long id);

}
