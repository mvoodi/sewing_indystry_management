package kg.alatoo.sewing_industry_management.dto;

import kg.alatoo.sewing_industry_management.enums.Status;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String style;
    private String color;
    private String size;
    private int quantity;
    private Status status;

    public ProductDTO(){

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
