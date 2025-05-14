package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.model.*;
import kg.alatoo.sewing_industry_management.enums.Status;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Test
    void testToDto() {
        RawMaterial rawMaterial = new RawMaterial();

        Product product = new Product();
        product.setId(1L);
        product.setName("T-Shirt");
        product.setStyle("Casual");
        product.setColor("White");
        product.setSize("M");
        product.setQuantity(50);
        product.setStatus(Status.INSTOCK);
        product.setRawMaterial(rawMaterial);


        ProductDTO dto = mapper.toDto(product);
        assertNotNull(dto);
        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getRawMaterial().getId(), dto.getRawMaterialId());

    }

    @Test
    void testToEntity() {
        ProductDTO dto = new ProductDTO(1L, "T-Shirt", "Casual", "White", "M", 50, Status.INSTOCK,1L);
        Product product = mapper.toEntity(dto);
        assertNotNull(product);
        assertEquals(dto.getId(), product.getId());
    }
}