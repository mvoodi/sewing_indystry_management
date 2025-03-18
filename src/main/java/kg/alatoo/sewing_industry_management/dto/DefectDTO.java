package kg.alatoo.sewing_industry_management.dto;

import lombok.Data;

@Data
public class DefectDTO {
    private Long id;
    private String description;
    private int quantity;
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
