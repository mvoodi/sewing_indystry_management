package kg.alatoo.sewing_industry_management.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;


class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleDefectNotFoundException() {
        DefectNotFoundException exception = new DefectNotFoundException("Defect not found");
        ResponseEntity<String> response = handler.handleDefectNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Defect not found", response.getBody());
    }

    @Test
    void handleProductNotFoundException() {
        ProductNotFoundException exception = new ProductNotFoundException("Product not found");
        ResponseEntity<String> response = handler.handleProductNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }

    @Test
    void handleRawMaterialNotFoundException() {
        RawMaterialNotFoundException exception = new RawMaterialNotFoundException("Raw material not found");
        ResponseEntity<String> response = handler.handleRawMaterialNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Raw material not found", response.getBody());
    }

    @Test
    void handleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        ResponseEntity<String> response = handler.handleUserNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void handleGeneralException() {
        Exception exception = new Exception("Some error occurred");
        ResponseEntity<String> response = handler.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody());
    }

}
