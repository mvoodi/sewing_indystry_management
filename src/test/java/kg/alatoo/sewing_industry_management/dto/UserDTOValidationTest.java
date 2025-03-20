package kg.alatoo.sewing_industry_management.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kg.alatoo.sewing_industry_management.enums.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserDTO() {
        UserDTO user = new UserDTO(null, "Aibek", "password123", "aibek.bekov@example.com", Role.CUTTER);
        Set<jakarta.validation.ConstraintViolation<UserDTO>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testUserDTOWithBlankUsername() {
        UserDTO user = new UserDTO(null, "", "aibek.bekov@example.com", "password123", Role.CUTTER);
        Set<jakarta.validation.ConstraintViolation<UserDTO>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Expected validation error for blank username");
    }

    @Test
    void testUserDTOWithInvalidEmail() {
        UserDTO user = new UserDTO(null, "Aibek", "password123", "invalid-email", Role.CUTTER);
        Set<jakarta.validation.ConstraintViolation<UserDTO>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Expected validation error for invalid email");
    }

    @Test
    void testUserDTOWithShortPassword() {
        UserDTO user = new UserDTO(null, "Aibek", "123", "aibek.bekov@example.com", Role.CUTTER);
        Set<jakarta.validation.ConstraintViolation<UserDTO>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Expected validation error for short password");
    }
}