package kg.alatoo.sewing_industry_management.dto;

import kg.alatoo.sewing_industry_management.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Role cannot be null")
    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
