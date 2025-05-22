package kg.alatoo.sewing_industry_management.auth.service;

import kg.alatoo.sewing_industry_management.model.User;

public interface JwtService {
    String generateToken(User user);
    boolean validateToken(String token);
    String extractUsername(String token);
}