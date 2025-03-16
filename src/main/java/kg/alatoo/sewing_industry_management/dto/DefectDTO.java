package kg.alatoo.sewing_industry_management.dto;

import lombok.Data;

@Data
public class DefectDTO {
    private Long id;
    private String description;
    private int quantity;
    private Long productId;

}
