package kg.alatoo.sewing_industry_management.dto;

import kg.alatoo.sewing_industry_management.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    /* ── Личные данные ────────────────────────────── */
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 50, message = "First name must be 2-50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be 2-50 characters")
    private String lastName;

    /* ── Учётные данные ───────────────────────────── */
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    /* ── Роль ─────────────────────────────────────── */
    @NotNull(message = "Role cannot be null")
    private Role role;


    /* ── Конструкторы ─────────────────────────────── */
    public UserDTO() {}

    public UserDTO(Long id,
                   String firstName,
                   String lastName,
                   String username,
                   String password,
                   String email,
                   Role role) {

        this.id = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.password  = password;
        this.email     = email;
        this.role      = role;
    }
}
