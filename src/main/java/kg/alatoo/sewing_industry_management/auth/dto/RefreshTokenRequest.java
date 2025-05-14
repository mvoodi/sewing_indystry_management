package kg.alatoo.sewing_industry_management.auth.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RefreshTokenRequest {
    private String refreshToken;
}
