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
class DefectRepositoryTest {

    @Autowired
    private DefectRepository defectRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    private Defect defect;
    private Product product;
    private RawMaterial rawMaterial;

    @BeforeEach
    void setUp() {
        rawMaterial = new RawMaterial();
        rawMaterial.setName("Cotton");
        rawMaterial.setColor("White");
        rawMaterial.setQuantity(100);
        rawMaterial.setDescription("Soft cotton fabric");
        rawMaterial = rawMaterialRepository.save(rawMaterial);

        product = new Product();
        product.setName("T-Shirt");
        product.setStyle("Casual");
        product.setColor("White");
        product.setSize("M");
        product.setQuantity(50);
        product.setStatus("Available");
        product.setRawMaterial(rawMaterial);
        product = productRepository.save(product);

        defect = new Defect();
        defect.setDescription("Small hole");
        defect.setQuantity(2);
        defect.setProduct(product);
        defect = defectRepository.save(defect);
    }

    @Test
    void testSaveDefect() {
        Optional<Defect> foundDefect = defectRepository.findById(defect.getId());
        assertTrue(foundDefect.isPresent());
        assertEquals("Small hole", foundDefect.get().getDescription());
    }

    @Test
    void testDeleteDefect() {
        defectRepository.delete(defect);
        Optional<Defect> deletedDefect = defectRepository.findById(defect.getId());
        assertFalse(deletedDefect.isPresent());
    }
}
