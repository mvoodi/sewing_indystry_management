package kg.alatoo.sewing_industry_management.auth;


import kg.alatoo.sewing_industry_management.auth.dto.*;
import kg.alatoo.sewing_industry_management.auth.model.*;
import kg.alatoo.sewing_industry_management.auth.repository.RefreshTokenRepository;
import kg.alatoo.sewing_industry_management.auth.repository.TwoFactorTokenRepository;
import kg.alatoo.sewing_industry_management.auth.repository.VerificationTokenRepository;
import kg.alatoo.sewing_industry_management.auth.security.JwtUtil;
import kg.alatoo.sewing_industry_management.auth.service.AuthServiceImpl;
import kg.alatoo.sewing_industry_management.auth.service.EmailService;
import kg.alatoo.sewing_industry_management.auth.service.TwoFaServiceImpl;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.model.User;
import kg.alatoo.sewing_industry_management.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock UserRepository userRepo;
    @Mock
    VerificationTokenRepository verifyRepo;
    @Mock
    RefreshTokenRepository refreshRepo;
    @Mock
    TwoFactorTokenRepository twoFaTokenRepo;
    @Mock PasswordEncoder encoder;
    @Mock AuthenticationManager authManager;
    @Mock
    JwtUtil jwtUtil;
    @Mock
    EmailService emailService;
    @Mock
    TwoFaServiceImpl twoFaService;

    @InjectMocks
    AuthServiceImpl authService;

    private RegisterRequest registerReq;
    private User user;

    @BeforeEach
    void setUp() {
        registerReq = RegisterRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .email("john@doe.com")
                .password("pass123")
                .build();

        user = User.builder()
                .username("john")
                .email("john@doe.com")
                .role(Role.MANAGER)
                .build();
    }

    @Test
    void register_Success() {
        when(userRepo.findByEmail(registerReq.getEmail()))
                .thenReturn(Optional.empty());
        when(encoder.encode(registerReq.getPassword()))
                .thenReturn("ENCODED");
        // act
        AuthResponse resp = authService.register(registerReq);
        // assert
        assertNull(resp.getAccessToken());
        assertNull(resp.getRefreshToken());
        verify(userRepo).save(any(User.class));
        verify(verifyRepo).save(any(VerificationToken.class));
        verify(emailService).sendVerifyEmail(eq("john@doe.com"), anyString());
    }

    @Test
    void login_No2FA_Success() {
        LoginRequest req = new LoginRequest("john@doe.com", "pass");
        User u = User.builder()
                .username("john")
                .email("john@doe.com")
                .role(Role.MANAGER)
                .build();
        // authManager не бросает
        when(userRepo.findByEmail("john@doe.com")).thenReturn(Optional.of(u));
        when(jwtUtil.generateToken(Map.of("roles",Role.MANAGER), u))
                .thenReturn("ACCESS_TOK");
        // act
        AuthResponse resp = authService.login(req);
        // assert
        assertEquals("ACCESS_TOK", resp.getAccessToken());
        assertNotNull(resp.getRefreshToken());
    }

    @Test
    void login_With2FA_Requires2FA() {
        LoginRequest req = new LoginRequest("john@doe.com", "pass");
        User u = User.builder()
                .username("john").email("john@doe.com")
                .twoFactorEnabled(true)
                .role(Role.MANAGER)
                .build();
        when(userRepo.findByEmail("john@doe.com")).thenReturn(Optional.of(u));
        // act
        AuthResponse resp = authService.login(req);
        assertEquals("2FA_REQUIRED", resp.getAccessToken());
        assertNull(resp.getRefreshToken());
    }

    @Test
    void refreshToken_Success() {
        RefreshToken rt = RefreshToken.builder()
                .token("R1")
                .expiryDate(Instant.now().plusSeconds(60))
                .user(user)
                .build();
        when(refreshRepo.findByToken("R1")).thenReturn(Optional.of(rt));
        when(jwtUtil.generateToken(Map.of("roles", Role.MANAGER), user))
                .thenReturn("NEW_ACC");
        AuthResponse resp = authService.refresh("R1");
        assertEquals("NEW_ACC", resp.getAccessToken());
        assertEquals("R1", resp.getRefreshToken());
    }

    @Test
    void verifyEmail_Success() {
        User u2 = User.builder().email("x@x").build();
        VerificationToken vt = VerificationToken.builder()
                .token("T1")
                .expiryDate(Instant.now().plusSeconds(60))
                .user(u2).build();
        when(verifyRepo.findByToken("T1")).thenReturn(Optional.of(vt));
        authService.verifyEmail("T1");
        assertTrue(u2.isEmailVerified());
        verify(verifyRepo).delete(vt);
    }
}
