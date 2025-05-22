package kg.alatoo.sewing_industry_management.service;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;

import java.util.List;

public interface RawMaterialService {
    List<RawMaterialDTO> getAllRawMaterials();
    RawMaterialDTO getRawMaterialById(Long id);
    RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO);
    RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO);
    void deleteRawMaterial(Long id);
}