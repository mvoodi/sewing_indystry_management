package kg.alatoo.sewing_industry_management.auth.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import kg.alatoo.sewing_industry_management.auth.model.TwoFactorToken;
import kg.alatoo.sewing_industry_management.model.User;
import kg.alatoo.sewing_industry_management.auth.repository.TwoFactorTokenRepository;
import kg.alatoo.sewing_industry_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TwoFaServiceImpl {

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private final UserRepository userRepo;
    private final TwoFactorTokenRepository tokenRepo;

    /** Включить 2FA: возвращаем QR-ссылку для приложения Google Authenticator */
    public String enable2FA(User user) {
        var creds = gAuth.createCredentials();
        user.setTwoFactorEnabled(true);
        user.setTwoFaSecret(creds.getKey());
        userRepo.save(user);

        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(
                "SewingApp", user.getEmail(), creds);
    }

    /** Проверяем 6-значный код и создаём одноразовый токен (60 сек) */
    public boolean verifyCode(User user, int code) {
        boolean ok = gAuth.authorize(user.getTwoFaSecret(), code);
        if (ok) {
            tokenRepo.save(
                    TwoFactorToken.builder()
                            .user(user)
                            .code(String.valueOf(code))           // ← имя поля
                            .expiryDate(Instant.now().plusSeconds(60))
                            .build());
        }
        return ok;
    }
}
