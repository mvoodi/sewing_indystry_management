package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.entities.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void testToDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("JohnDoe");
        user.setPassword("password");
        user.setEmail("john@example.com");
        user.setRole("USER");

        UserDTO dto = mapper.toDto(user);
        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
    }

    @Test
    void testToEntity() {
        UserDTO dto = new UserDTO(1L, "JohnDoe", "password", "john@example.com", "USER");
        User user = mapper.toEntity(dto);
        assertNotNull(user);
        assertEquals(dto.getId(), user.getId());
    }
}
