package kg.alatoo.sewing_industry_management.mappers;


import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.entities.Defect;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DefectMapper {
    DefectMapper INSTANCE = Mappers.getMapper(DefectMapper.class);

    DefectDTO toDto(Defect defect);
    Defect toEntity(DefectDTO defectDTO);
}
