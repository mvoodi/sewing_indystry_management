package kg.alatoo.sewing_industry_management.repositories;

import kg.alatoo.sewing_industry_management.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RawMaterialRepositoryTest {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    private RawMaterial rawMaterial;

    @BeforeEach
    void setUp() {
        rawMaterial = new RawMaterial();
        rawMaterial.setName("Cotton");
        rawMaterial.setColor("White");
        rawMaterial.setQuantity(100);
        rawMaterial.setDescription("Soft cotton fabric");
        rawMaterial = rawMaterialRepository.save(rawMaterial);
    }

    @Test
    void testSaveRawMaterial() {
        Optional<RawMaterial> foundMaterial = rawMaterialRepository.findById(rawMaterial.getId());
        assertTrue(foundMaterial.isPresent());
        assertEquals("Cotton", foundMaterial.get().getName());
    }

    @Test
    void testDeleteRawMaterial() {
        rawMaterialRepository.delete(rawMaterial);
        Optional<RawMaterial> deletedMaterial = rawMaterialRepository.findById(rawMaterial.getId());
        assertFalse(deletedMaterial.isPresent());
    }
}
