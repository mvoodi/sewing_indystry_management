package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.entities.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RawMaterialMapperTest {
    private final RawMaterialMapper mapper = RawMaterialMapper.INSTANCE;

    @Test
    void testToDto() {
        RawMaterial material = new RawMaterial();
        material.setId(1L);
        material.setName("Cotton");
        material.setColor("White");
        material.setQuantity(100);
        material.setStatus("Soft cotton fabric");

        RawMaterialDTO dto = mapper.toDto(material);
        assertNotNull(dto);
        assertEquals(material.getId(), dto.getId());
    }

    @Test
    void testToEntity() {
        RawMaterialDTO dto = new RawMaterialDTO(1L, "Cotton", "White", 100, "Soft cotton fabric");
        RawMaterial material = mapper.toEntity(dto);
        assertNotNull(material);
        assertEquals(dto.getId(), material.getId());
    }
}
