package kg.alatoo.sewing_industry_management.dto;

import lombok.Data;

@Data
public class RawMaterialDTO {
    private Long id;
    private String name;
    private String color;
    private double quantity;
    private String description;
}