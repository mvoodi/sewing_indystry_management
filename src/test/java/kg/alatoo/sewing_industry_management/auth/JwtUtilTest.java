package kg.alatoo.sewing_industry_management.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kg.alatoo.sewing_industry_management.auth.security.JwtUtil;
import kg.alatoo.sewing_industry_management.config.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        // Генерируем безопасный 256-битный ключ для HMAC-SHA256
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        JwtProperties props = new JwtProperties();
        props.setSecret(base64Key);
        props.setExpiration(1000 * 60L); // 1 минута
        jwtUtil = new JwtUtil(props);
    }

    @Test
    void generate_and_validate_token() {
        // Создаём тестового пользователя с одним authority
        User user = new User(
                "alice",
                "pwd",
                java.util.List.of(() -> "ROLE_USER")
        );

        String token = jwtUtil.generateToken(Map.of("foo", "bar"), user);

        assertEquals("alice", jwtUtil.extractUsername(token));
        assertTrue(jwtUtil.isTokenValid(token, user));
    }

    @Test
    void token_expires() throws InterruptedException {
        // Ключ генерируем заново, но выставляем expiry в 10 мс
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        JwtProperties props = new JwtProperties();
        props.setSecret(base64Key);
        props.setExpiration(10L); // 10 миллисекунд

        JwtUtil util = new JwtUtil(props);
        User user = new User("bob", "pwd", java.util.List.of());

        String token = util.generateToken(Map.of(), user);
        Thread.sleep(20);

        assertFalse(util.isTokenValid(token, user), "Token should be expired after 20 ms");
    }
}
