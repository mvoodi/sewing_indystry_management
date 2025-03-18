package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.entities.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Test
    void testToDto() {
        Product product = new Product();
        product.setId(1L);
        product.setName("T-Shirt");
        product.setStyle("Casual");
        product.setColor("White");
        product.setSize("M");
        product.setQuantity(50);
        product.setStatus("Available");

        ProductDTO dto = mapper.toDto(product);
        assertNotNull(dto);
        assertEquals(product.getId(), dto.getId());
    }

    @Test
    void testToEntity() {
        ProductDTO dto = new ProductDTO(1L, "T-Shirt", "Casual", "White", "M", 50, "Available");
        Product product = mapper.toEntity(dto);
        assertNotNull(product);
        assertEquals(dto.getId(), product.getId());
    }
}