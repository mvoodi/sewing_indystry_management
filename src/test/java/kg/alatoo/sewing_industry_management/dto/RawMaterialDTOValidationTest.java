package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RawMaterialDTOValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRawMaterialDTO() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "Cotton", "red", 500.0, "INSTOCK");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testRawMaterialDTOWithNegativeQuantity() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "Cotton", "red", -50.0, "INSTOCK");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertFalse(violations.isEmpty(), "Expected validation error for negative quantity");
    }

    @Test
    void testRawMaterialDTOWithZeroQuantity() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "Cotton", "red", 0.0, "INSTOCK");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertFalse(violations.isEmpty(), "Expected validation error for zero quantity");
    }

    @Test
    void testRawMaterialDTOWithBlankName() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "", "red", 500.0, "INSTOCK");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertFalse(violations.isEmpty(), "Expected validation error for blank name");
    }

    @Test
    void testRawMaterialDTOWithBlankColor() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "Cotton", "", 500.0, "INSTOCK");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertFalse(violations.isEmpty(), "Expected validation error for blank color");
    }

    @Test
    void testRawMaterialDTOWithBlankUnit() {
        RawMaterialDTO rawMaterial = new RawMaterialDTO(null, "Cotton", "red", 500.0, "");
        Set<jakarta.validation.ConstraintViolation<RawMaterialDTO>> violations = validator.validate(rawMaterial);
        assertFalse(violations.isEmpty(), "Expected validation error for blank unit");
    }
}
