package kg.alatoo.sewing_industry_management.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RawMaterialNotFoundExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRawMaterialNotFoundExceptionMessage() {
        RawMaterialNotFoundException exception = new RawMaterialNotFoundException("Raw material not found");
        assertEquals("Raw material not found", exception.getMessage());
    }
}