package kg.alatoo.sewing_industry_management.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String emailOrUsername;
    private String password;
}