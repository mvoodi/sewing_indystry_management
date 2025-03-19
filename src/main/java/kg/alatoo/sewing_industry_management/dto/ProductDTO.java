package kg.alatoo.sewing_industry_management.dto;

import kg.alatoo.sewing_industry_management.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Name should not be blank")
    private String name;

    private String style;

    @NotBlank(message = "Color should not be blank")
    private String color;

    @NotBlank(message = "Size should not be blank")
    private String size;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Status should not be null")
    private Status status;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String style, String color, String size, int quantity, Status status) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.status = status;
    }
}
