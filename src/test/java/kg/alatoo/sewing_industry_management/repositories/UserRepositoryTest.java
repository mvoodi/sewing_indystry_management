package kg.alatoo.sewing_industry_management.repositories;

import kg.alatoo.sewing_industry_management.entities.User;
import kg.alatoo.sewing_industry_management.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Gulya");
        user.setPassword("admin123");
        user.setEmail("gulyaK@example.com");
        user.setRole(Role.SEAMSTRESS);
    }

    @Test
    void testFindById() {
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("Gulya", foundUser.get().getUsername());
    }

    @Test
    void testSaveUser() {
        User newUser = new User();
        user.setUsername("Tunuk");
        user.setPassword("Tunuk1999");
        user.setEmail("Tunukk99@example.com");
        user.setRole(Role.SEAMSTRESS);

        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser.getId());
        assertEquals("Tunuk", savedUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}
