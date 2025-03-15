package kg.alatoo.sewing_industry_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
