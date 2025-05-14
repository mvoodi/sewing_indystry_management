package kg.alatoo.sewing_industry_management.auth.controller;

import kg.alatoo.sewing_industry_management.auth.dto.*;
import kg.alatoo.sewing_industry_management.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Please check your email to verify your account.");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok("Email verified successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/2fa")
    public ResponseEntity<AuthResponse> verify2FA(@RequestBody TwoFactorRequest request) {
        return ResponseEntity.ok(authService.verifyTwoFactorCode(request));
    }



    @PostMapping("/2fa/enable")
    public ResponseEntity<String> enable2fa(@RequestParam("email") String email) {
        String qrUrl = authService.enable2fa(email);
        return ResponseEntity.ok(qrUrl);
    }

    @PostMapping("/2fa/verify")
    public ResponseEntity<AuthResponse> verify2fa(@RequestBody TwoFactorRequest req) {
        AuthResponse auth = authService.verify2fa(req);
        return ResponseEntity.ok(auth);
    }

}
