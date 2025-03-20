package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DefectDTOValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDefectDTO() {
        DefectDTO defect = new DefectDTO(null,"Stains on the fabric", 100, 1L);
        Set<jakarta.validation.ConstraintViolation<DefectDTO>> violations = validator.validate(defect);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testDefectDTOWithBlankDescription() {
        DefectDTO defect = new DefectDTO(null,"", 100, 1L);
        Set<jakarta.validation.ConstraintViolation<DefectDTO>> violations = validator.validate(defect);
        assertFalse(violations.isEmpty(), "Expected validation error for blank defect description");
    }

    @Test
    void testDefectDTOWithNegativeQuantity() {
        DefectDTO defect = new DefectDTO(null,"Stains on the fabric", -100, 1L);
        Set<jakarta.validation.ConstraintViolation<DefectDTO>> violations = validator.validate(defect);
        assertFalse(violations.isEmpty(), "Expected validation error for negative quantity");
    }

    @Test
    void testDefectDTOWithNullQuantity() {
        DefectDTO defect = new DefectDTO(null,"Stains on the fabric", 0, 1L);
        Set<jakarta.validation.ConstraintViolation<DefectDTO>> violations = validator.validate(defect);
        assertFalse(violations.isEmpty(), "Expected validation error for null quantity");
    }

    @Test
    void testDefectDTOWithBlankProductID() {
        DefectDTO defect = new DefectDTO(null,"Stains on the fabric", 100, null);        Set<jakarta.validation.ConstraintViolation<DefectDTO>> violations = validator.validate(defect);
        assertFalse(violations.isEmpty(), "Expected validation error for blank productID");
    }
}
