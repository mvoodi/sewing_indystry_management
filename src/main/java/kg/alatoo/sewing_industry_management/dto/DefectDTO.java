package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.constraints.NotNull;
import  jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DefectDTO {
    private Long id;

    @NotBlank(message = "Description should not be blank")
    private String description;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Product ID should not be null")
    private Long productId;


    public DefectDTO(){

    }

    public DefectDTO(Long id, String description, int quantity, Long productId) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.productId = productId;
    }
}
