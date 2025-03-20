package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RawMaterialDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Color cannot be empty")
    private String color;

    @Positive(message = "Quantity must be positive")
    private double quantity;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    public RawMaterialDTO() {
    }

    public RawMaterialDTO(Long id, String name, String color, double quantity, String status) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.status = status;
    }
}
