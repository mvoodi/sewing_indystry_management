package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.*;
import kg.alatoo.sewing_industry_management.entities.*;
import kg.alatoo.sewing_industry_management.enums.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void testToDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Aika");
        user.setPassword("admin123");
        user.setEmail("aika@example.com");
        user.setRole(Role.ADMIN);

        UserDTO dto = mapper.toDto(user);
        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
    }

    @Test
    void testToEntity() {
        UserDTO dto = new UserDTO(1L, "Aika", "admin123", "aika@example.com", Role.ADMIN);
        User user = mapper.toEntity(dto);
        assertNotNull(user);
        assertEquals(dto.getId(), user.getId());
    }
}
