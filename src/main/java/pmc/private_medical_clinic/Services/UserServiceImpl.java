package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AuthResponse;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Entity.UserGroup;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.failureHandler.GlobalExceptionHandler;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setHoTen(userDto.getHoTen());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserGroup group = new UserGroup();
        group.setId(userDto.getMaNhom());
        user.setMaNhom(group);
        user.setEmail(userDto.getEmail());
        userRepo.save(user);

        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken.getRefreshToken());
        return user;
    }

    @Override
    public User updateInfo(UserDto userDto, Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new GlobalExceptionHandler("User not found"));
        user.setEmail(userDto.getEmail());
        user.setHoTen(userDto.getHoTen());
        userRepo.save(user);
        return user;
    }


    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() ->new GlobalExceptionHandler("email not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new GlobalExceptionHandler("User not found"));
    }

    @Override
    public User changePassword(UserDto userDto, Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new GlobalExceptionHandler("User not found"));
        if(!(passwordEncoder.matches(userDto.getPassword(), user.getPassword()))) {
            throw new GlobalExceptionHandler("Incorrect password");
        }
        else if(!(userDto.getNewPassword().equals(userDto.getRepeatNewPassword()))) {
            throw new GlobalExceptionHandler("Incorrect repeat password");
        }
        else {
            user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        }
        return userRepo.save(user);
    }
}

