package pmc.private_medical_clinic.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.spring6.processor.SpringActionTagProcessor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nguoidung")
public class User implements UserDetails {
    @Id
    @Column(name="U_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long uId;

    @Column(length = 60, name = "username", unique = true)
    private String username;

    @Column(length = 60, name = "password")
    @Size(min = 5, message = "the password must have at least 5 characters")
    private String password;

    @Column(length = 60, name = "ho_ten")
    private String hoTen;

    @Column(length = 60, name = "Email", unique = true)
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name = "usergroup_id")
    private UserGroup maNhom;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
