package kg.alatoo.sewing_industry_management.auth.repository;

import kg.alatoo.sewing_industry_management.auth.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
