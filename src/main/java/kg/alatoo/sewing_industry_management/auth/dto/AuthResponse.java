package kg.alatoo.sewing_industry_management.auth.dto;

import lombok.*;
@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;  // "Bearer"
    private long   expiresAt;  // Unix-time ms


}
