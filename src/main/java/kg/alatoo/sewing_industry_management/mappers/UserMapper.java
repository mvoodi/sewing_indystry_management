package kg.alatoo.sewing_industry_management.mappers;


import kg.alatoo.sewing_industry_management.dto.UserDTO;
import kg.alatoo.sewing_industry_management.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);


}
