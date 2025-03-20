package kg.alatoo.sewing_industry_management.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNotFoundExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testProductNotFoundExceptionMessage() {
        ProductNotFoundException exception = new ProductNotFoundException("Product not found");
        assertEquals("Product not found", exception.getMessage());
    }

}