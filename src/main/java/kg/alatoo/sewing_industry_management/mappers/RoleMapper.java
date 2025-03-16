package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.RoleDTO;
import kg.alatoo.sewing_industry_management.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDto(Role role);
    Role toEntity(RoleDTO roleDTO);
}
