package kg.alatoo.sewing_industry_management.dto;

import kg.alatoo.sewing_industry_management.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public UserDTO(){

    }



    public UserDTO(Long id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
