package kg.alatoo.sewing_industry_management.services;


import kg.alatoo.sewing_industry_management.dto.UserDTO;
import kg.alatoo.sewing_industry_management.entities.Role;
import kg.alatoo.sewing_industry_management.entities.User;
import kg.alatoo.sewing_industry_management.mappers.UserMapper;
import kg.alatoo.sewing_industry_management.repositories.RoleRepository;
import kg.alatoo.sewing_industry_management.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);


    }

    public UserDTO updateUser(Long id, UserDTO userDTO){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());

        if (userDTO.getRoles() != null) {
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            existingUser.setRoles(roles);
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}