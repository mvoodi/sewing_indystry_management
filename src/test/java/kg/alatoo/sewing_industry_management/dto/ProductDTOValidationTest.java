package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kg.alatoo.sewing_industry_management.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class ProductDTOValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProductDTO() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "white", "44", 250, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }
    @Test
    void testProductDTOWithBlankName() {
        ProductDTO product = new ProductDTO(null, "", "Oversized", "white", "44", -250, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for blank name");
    }

    @Test
    void testProductDTOWithBlankColor() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "", "44", -250, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for blank color");
    }

    @Test
    void testProductDTOWithBlankSize() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "white", "", -250, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for blank size");
    }

    @Test
    void testProductDTOWithNegativeQuantity() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "white", "44", -250, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for negative quantity");
    }

    @Test
    void testProductDTOWithZeroQuantity() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "white", "44", 0, Status.SEWING);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for zero stock");
    }

    @Test
    void testProductDTOWithBlankStatus() {
        ProductDTO product = new ProductDTO(null, "T-Shirt", "Oversized", "white", "44", 250, null);
        Set<jakarta.validation.ConstraintViolation<ProductDTO>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Expected validation error for blank status");
    }
}