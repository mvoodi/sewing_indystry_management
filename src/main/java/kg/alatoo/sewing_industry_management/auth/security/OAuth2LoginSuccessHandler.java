package kg.alatoo.sewing_industry_management.auth.security;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.model.User;
import kg.alatoo.sewing_industry_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        if (StringUtils.isBlank(email)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found from OAuth2 provider");
            return;
        }

        User user = userRepo.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .username(email)
                            .firstName(oauthUser.getAttribute("given_name"))
                            .lastName(oauthUser.getAttribute("family_name"))
                            .role(Role.ADMIN)
                            .emailVerified(true)
                            .enabled(true)
                            .build();
                    return userRepo.save(newUser);
                });

        // 2) Генерируем JWT
        String token = jwtUtil.generateToken(Map.of("roles", user.getRole().name()),
                user);

        // 3) Возвращаем токен в ответе (JSON)
        response.setContentType("application/json");
        response.getWriter().write("{\"accessToken\":\"" + token + "\"}");
    }
}
