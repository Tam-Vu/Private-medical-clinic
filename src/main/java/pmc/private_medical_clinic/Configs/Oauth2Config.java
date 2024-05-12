package pmc.private_medical_clinic.Configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import pmc.private_medical_clinic.Dto.AuthResponse;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.UserRepo;
import pmc.private_medical_clinic.Services.JwtService;
import pmc.private_medical_clinic.Services.RefreshTokenService;

import java.io.IOException;

@Configuration
@CrossOrigin(origins = "*")
public class Oauth2Config extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            User user = userRepo.findByEmail(oAuth2User.getAttribute("email").toString()).orElse(null);
            if (user != null) {
                var token = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
                AuthResponse authResponse = new AuthResponse();
                authResponse.setAccessToken(token);
                authResponse.setRefreshToken(refreshToken.getRefreshToken());
                response.getWriter().write(authResponse.toString());
                response.getWriter().flush();
            }
            else {
                response.sendError(500, "user not found");
            }
        } else {
            response.sendError(500, "server error");
        }
    }
}
