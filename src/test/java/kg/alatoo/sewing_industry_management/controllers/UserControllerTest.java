package kg.alatoo.sewing_industry_management.controllers;

import kg.alatoo.sewing_industry_management.dto.UserDTO;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1L, "testuser", "password123", "test@example.com", Role.ADMIN);  // Инициализируем объект UserDTO
    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDTO));

        var result = userController.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
    }

    @Test
    void getUserById() {

        when(userService.getUserById(1L)).thenReturn(userDTO);


        ResponseEntity<UserDTO> result = userController.getUserById(1L);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("testuser", result.getBody().getUsername());
    }

    @Test
    void createUser() {
        when(userService.createUser(userDTO)).thenReturn(userDTO);


        ResponseEntity<UserDTO> result = userController.createUser(userDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("testuser", result.getBody().getUsername());
    }

    @Test
    void updateUser() {

        when(userService.updateUser(1L, userDTO)).thenReturn(userDTO);
        ResponseEntity<UserDTO> result = userController.updateUser(1L, userDTO);


        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("testuser", result.getBody().getUsername());
    }

    @Test
    void deleteUser() {
        doNothing().when(userService).deleteUser(1L);


        ResponseEntity<Void> result = userController.deleteUser(1L);


        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(1L);
    }
}
