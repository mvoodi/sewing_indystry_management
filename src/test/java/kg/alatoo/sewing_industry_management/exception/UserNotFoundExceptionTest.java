package kg.alatoo.sewing_industry_management.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUserNotFoundExceptionMessage() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        assertEquals("User not found", exception.getMessage());
    }
}