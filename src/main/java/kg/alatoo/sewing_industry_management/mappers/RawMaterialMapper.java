package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {
    RawMaterialMapper INSTANCE = Mappers.getMapper(RawMaterialMapper.class);

    RawMaterialDTO toDto(RawMaterial rawMaterial);
    RawMaterial toEntity(RawMaterialDTO rawMaterialDTO);
}