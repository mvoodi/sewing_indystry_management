package kg.alatoo.sewing_industry_management.model;

import jakarta.persistence.*;
import kg.alatoo.sewing_industry_management.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean enabled;

    private boolean emailVerified;

    private boolean twoFactorEnabled;

    @Column(name = "two_fa_secret")
    private String twoFaSecret;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return enabled;
    }

    public boolean isTwoFaEnabled() {
        return twoFactorEnabled;
    }


    public String getTwoFaSecret() {
        return twoFaSecret;
    }
    public void setTwoFaSecret(String secret) {
        this.twoFaSecret = secret;
    }


}
