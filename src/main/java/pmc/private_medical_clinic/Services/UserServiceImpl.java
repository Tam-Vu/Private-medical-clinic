package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setHoTen(userDto.getHoTen());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMaNhom(userDto.getMaNhom());
        user.setEmail(userDto.getEmail());
        userRepo.save(user);
        return user;
    }

    @Override
    public User updateInfo(UserDto userDto, Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        user.setEmail(userDto.getEmail());
        user.setHoTen(userDto.getHoTen());
        userRepo.save(user);
        return user;
    }


    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User changePassword(UserDto userDto, Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        if(!userDto.getNewPassword().equals(userDto.getRepeatNewPassword())) {
            throw new IncorrectPasswordException("Incorrect repeat password");
        }
        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        return userRepo.save(user);
    }
}

