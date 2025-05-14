package kg.alatoo.sewing_industry_management.auth.service;

import kg.alatoo.sewing_industry_management.auth.dto.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    void verifyEmail(String token);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    AuthResponse verifyTwoFactorCode(TwoFactorRequest request);

    /* ───────── refresh ───────── */
    AuthResponse refresh(String refreshToken);

    String enable2fa(String email);

    AuthResponse verify2fa(TwoFactorRequest req);
}
