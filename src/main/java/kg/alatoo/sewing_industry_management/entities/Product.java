package kg.alatoo.sewing_industry_management.entities;

import jakarta.persistence.*;
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
    private String status;
    @ManyToOne
    @JoinColumn(name = "raw_material_id")
    private RawMaterial rawMaterial; //Connection with raw material
}
