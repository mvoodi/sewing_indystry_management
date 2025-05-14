package kg.alatoo.sewing_industry_management.auth.service;

import kg.alatoo.sewing_industry_management.auth.dto.*;
import kg.alatoo.sewing_industry_management.auth.model.*;
import kg.alatoo.sewing_industry_management.auth.repository.RefreshTokenRepository;
import kg.alatoo.sewing_industry_management.auth.repository.TwoFactorTokenRepository;
import kg.alatoo.sewing_industry_management.auth.repository.VerificationTokenRepository;
import kg.alatoo.sewing_industry_management.auth.security.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import kg.alatoo.sewing_industry_management.auth.service.AuthService;
import kg.alatoo.sewing_industry_management.auth.service.EmailService;
import kg.alatoo.sewing_industry_management.auth.service.TwoFaServiceImpl;
import kg.alatoo.sewing_industry_management.model.User;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final UserRepository               userRepo;
    private final VerificationTokenRepository verifyRepo;
    private final RefreshTokenRepository refreshRepo;
    private final TwoFactorTokenRepository twoFaTokenRepo;

    private final PasswordEncoder          encoder;
    private final AuthenticationManager    authManager;
    private final JwtUtil                  jwt;
    private final EmailService emailService;
    private final TwoFaServiceImpl twoFa;

    /* ───────── регистрация ───────── */
    @Override
    public AuthResponse register(RegisterRequest req) {

        if (userRepo.findByEmail(req.getEmail()).isPresent())
            throw new IllegalStateException("Email already used");

        User u = new User();
        u.setUsername(req.getUsername());
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(Role.ADMIN);
        userRepo.save(u);

        // письмо-верификации
        String token = UUID.randomUUID().toString();
        verifyRepo.save(VerificationToken.builder()
                .token(token)
                .expiryDate(Instant.now().plusSeconds(86_400))
                .user(u).build());
        emailService.sendVerifyEmail(u.getEmail(), token);

        // регистрация не возвращает JWT → даём пустой ответ
        return new AuthResponse(null, null, "Bearer", 0);
    }

    /* ───────── verify email ───────── */

    @Override
    @Transactional
    public void verifyEmail(String token) {
        VerificationToken vt = verifyRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        User user = vt.getUser();
        user.setEmailVerified(true);
        user.setEnabled(true);
        userRepo.save(user);
    }

    /* ───────── логин ───────── */
    @Override
    public AuthResponse login(LoginRequest req) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getEmailOrUsername(), req.getPassword()));

        User u = userRepo.findByEmail(req.getEmailOrUsername())
                .or(() -> userRepo.findByUsername(req.getEmailOrUsername()))
                .orElseThrow();

        // если включена 2FA — возвращаем заглушку, фронт должен прислать код
        if (u.isTwoFaEnabled()) {
            return new AuthResponse(
                    "2FA_REQUIRED", null, "Bearer", 0);
        }
        return buildJwtPair(u);
    }

    /* ───────── refresh (через DTO) ───────── */
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest req) {
        return refresh(req.getRefreshToken());
    }

    /* ───────── refresh (строка) ───────── */
    @Override
    public AuthResponse refresh(String refreshToken) {
        RefreshToken rt = refreshRepo.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Bad refresh"));

        if (rt.isRevoked() || rt.getExpiryDate().isBefore(Instant.now()))
            throw new IllegalStateException("Refresh expired");

        return buildAccessOnly(rt.getUser(), refreshToken);
    }

    /* ───────── enable 2FA ───────── */
    @Override
    public String enable2fa(String email) {
        User u = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return twoFa.enable2FA(u);      // возвращает QR-ссылку
    }

    /* ───────── verify 2FA ───────── */
    @Override
    public AuthResponse verifyTwoFactorCode(TwoFactorRequest req) {
        return verify2fa(req);
    }

    @Override
    public AuthResponse verify2fa(TwoFactorRequest req) {
        User u = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!twoFa.verifyCode(u, req.code()))
            throw new IllegalArgumentException("Bad 2FA code");

        return buildJwtPair(u);
    }

    /* ───────── helpers ───────── */
    private AuthResponse buildJwtPair(User u) {
        String access = jwt.generateToken(Map.of("roles", u.getRole()), u);
        String refresh = UUID.randomUUID().toString();

        refreshRepo.save(RefreshToken.builder()
                .token(refresh)
                .expiryDate(Instant.now().plusSeconds(60L * 60 * 24 * 30))
                .user(u).build());

        return new AuthResponse(access, refresh, "Bearer", 15 * 60);
    }

    private AuthResponse buildAccessOnly(User u, String refreshToken) {
        String access = jwt.generateToken(Map.of("roles", u.getRole()), u);
        return new AuthResponse(access, refreshToken, "Bearer", 15 * 60);
    }
}
