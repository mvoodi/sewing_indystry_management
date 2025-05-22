package kg.alatoo.sewing_industry_management.auth.repository;

import kg.alatoo.sewing_industry_management.auth.model.TwoFactorToken;
import kg.alatoo.sewing_industry_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwoFactorTokenRepository extends JpaRepository<TwoFactorToken, Long> {
    Optional<TwoFactorToken> findByUser(User user);
}

