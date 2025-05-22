package kg.alatoo.sewing_industry_management.auth.model;

import jakarta.persistence.*;
import kg.alatoo.sewing_industry_management.model.User;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "email_verification_tokens")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private Instant expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
