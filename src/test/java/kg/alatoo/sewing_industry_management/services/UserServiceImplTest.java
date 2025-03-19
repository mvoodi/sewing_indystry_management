package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.UserDTO;
import kg.alatoo.sewing_industry_management.entities.User;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.mappers.UserMapper;
import kg.alatoo.sewing_industry_management.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("Mira");
        user.setPassword("uulum2010");
        user.setEmail("mira_official@example.com");
        user.setRole(Role.MANAGER);

        userDTO = new UserDTO(1L, "Mira", "uulum2010", "mira_official@example.com", Role.MANAGER);
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        List<UserDTO> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("Mira", users.get(0).getUsername());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO foundUser = userService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals("Mira", foundUser.getUsername());
    }

    @Test
    void createUser() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO createdUser = userService.createUser(userDTO);
        assertNotNull(createdUser);
        assertEquals("Mira", createdUser.getUsername());
    }

    @Test
    void updateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO updatedUser = userService.updateUser(1L, userDTO);
        assertNotNull(updatedUser);
        assertEquals("Mira", updatedUser.getUsername());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }
}
