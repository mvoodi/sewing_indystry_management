package kg.alatoo.sewing_industry_management.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefectNotFoundExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testDefectNotFoundExceptionMessage() {
        DefectNotFoundException exception = new DefectNotFoundException("Defect not found");
        assertEquals("Defect not found", exception.getMessage());
    }


}