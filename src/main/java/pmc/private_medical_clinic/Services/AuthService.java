package pmc.private_medical_clinic.Services;

import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AuthResponse;
import pmc.private_medical_clinic.Dto.LoginDto;
import pmc.private_medical_clinic.Dto.LogoutRequest;
import pmc.private_medical_clinic.Entity.InvalidatedToken;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.InvalidatedTokenRepository;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.failureHandler.GlobalExceptionHandler;

import java.text.ParseException;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        AuthResponse authResponse = new AuthResponse();
        var user = userRepo.findByUsername(loginDto.getUsername()).orElseThrow(() -> new GlobalExceptionHandler("Username not found"));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginDto.getUsername());
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken.getRefreshToken());
        return  authResponse;
    }
    public void logout(LogoutRequest request)  throws ParseException, JOSEException {
        try{
            Claims claims = jwtService.extractAllClaims(request.getToken());
            String jit = (String) claims.getId();
            Date expiryTime = claims.getExpiration();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expirationTime(expiryTime).build();
            invalidatedTokenRepository.save(invalidatedToken);
        }
        catch (Exception e){
            throw new GlobalExceptionHandler("Error logging out");
        }

    }

}
