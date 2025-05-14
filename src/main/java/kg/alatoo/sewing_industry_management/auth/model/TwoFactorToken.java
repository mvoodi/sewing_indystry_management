package kg.alatoo.sewing_industry_management.auth.model;

import jakarta.persistence.*;
import kg.alatoo.sewing_industry_management.model.User;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwoFactorToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Instant expiryDate;

    @OneToOne
    private User user;
}
