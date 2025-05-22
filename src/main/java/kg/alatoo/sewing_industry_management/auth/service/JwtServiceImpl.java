// JwtServiceImpl.java
package kg.alatoo.sewing_industry_management.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kg.alatoo.sewing_industry_management.config.JwtProperties;
import kg.alatoo.sewing_industry_management.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Qualifier("jwtProperties")
    private final JwtProperties props;

    @Override
    public String generateToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + props.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, props.getSecret())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        // TODO: распарсить и проверить expiration, подпись
        return true;
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(props.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
