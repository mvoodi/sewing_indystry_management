package kg.alatoo.sewing_industry_management.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String style;
    private String color;
    private String size;
    private int quantity;
    private String status;


}
