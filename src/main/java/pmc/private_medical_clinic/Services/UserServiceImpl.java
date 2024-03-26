package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;

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
        user.setTenDangNhap(userDto.getTenDangNhap());
        user.setMatKhau(passwordEncoder.encode(userDto.getMatKhau()));
        user.setMaNhom(userDto.getMaNhom());
        user.setEmail(userDto.getEmail());
        userRepo.save(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByTenDangNhap(String tenDangNhap) {
        return userRepo.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public Boolean checkByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user != null;
    }

    @Override
    public boolean checkByTenDangNhap(String tenDangNhap) {
        User user = userRepo.findByTenDangNhap(tenDangNhap);
        return user != null;
    }

    @Override
    public boolean checkPassword(String email, String matKhau) {
        User user = userRepo.findByEmail(email);
        if(user.getMatKhau() == matKhau) {
            return true;
        }
        return false;
    }
}
