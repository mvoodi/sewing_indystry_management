package kg.alatoo.sewing_industry_management.entities;

import jakarta.persistence.*;
import kg.alatoo.sewing_industry_management.enums.Role;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
