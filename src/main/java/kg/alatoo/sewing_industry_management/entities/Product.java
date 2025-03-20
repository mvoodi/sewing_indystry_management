package kg.alatoo.sewing_industry_management.entities;

import jakarta.persistence.*;
import kg.alatoo.sewing_industry_management.enums.Status;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String style;
    private String color;
    private String size;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false)
    private RawMaterial rawMaterial;
}
