package kg.alatoo.sewing_industry_management.dto;

import lombok.Data;

@Data
public class RawMaterialDTO {
    private Long id;
    private String name;
    private String color;
    private double quantity;
    private String description;

    public RawMaterialDTO(){

    }

    public RawMaterialDTO(Long id, String name, String color, double quantity, String description) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.description = description;
    }
}