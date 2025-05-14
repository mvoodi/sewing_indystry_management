package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefectMapperTest {
    private final DefectMapper mapper = DefectMapper.INSTANCE;

    @Test
    void testToDto() {
        Product product = new Product();
        Defect defect = new Defect();
        defect.setId(1L);
        defect.setDescription("Small hole");
        defect.setQuantity(2);
        defect.setProduct(product);

        DefectDTO dto = mapper.toDto(defect);
        assertNotNull(dto);
        assertEquals(defect.getId(), dto.getId());
        assertEquals(defect.getProduct().getId(), dto.getProductId());

    }

    @Test
    void testToEntity() {
        DefectDTO dto = new DefectDTO(1L, "Small hole", 2, 1L);
        Defect defect = mapper.toEntity(dto);
        assertNotNull(defect);
        assertEquals(dto.getId(), defect.getId());
    }
}
