package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.mappers.RawMaterialMapper;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface RawMaterialService {
    List<RawMaterialDTO> getAllRawMaterials();
    RawMaterialDTO getRawMaterialById(Long id);
    RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO);
    RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO);
    void deleteRawMaterial(Long id);
}