package kg.alatoo.sewing_industry_management.repositories;

import kg.alatoo.sewing_industry_management.entities.User;
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
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setRole("USER");
        userRepository.save(user);
    }

    @Test
    void testFindById() {
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testSaveUser() {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setPassword("newpassword");
        newUser.setEmail("new@example.com");
        newUser.setRole("ADMIN");

        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser.getId());
        assertEquals("newuser", savedUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}
