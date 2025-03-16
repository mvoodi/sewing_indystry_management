package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.mappers.RawMaterialMapper;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RawMaterialService {

    private RawMaterialRepository rawMaterialRepository;

    private RawMaterialMapper rawMaterialMapper;

    public List<RawMaterialDTO> getAllRawMaterials(){
        return rawMaterialRepository.findAll()
                .stream()
                .map(rawMaterialMapper::toDto)
                .collect(Collectors.toList());
    }

    public RawMaterialDTO getRawMaterialById(Long id){
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw Material not found."));
        return rawMaterialMapper.toDto(rawMaterial);
    }

    public RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO){
        RawMaterial rawMaterial = rawMaterialMapper.toEntity(rawMaterialDTO);
        RawMaterial savedRawMaterial = rawMaterialRepository.save(rawMaterial);
        return rawMaterialMapper.toDto(savedRawMaterial);
    }

    public RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO){
        RawMaterial existingRawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw Material not found"));
        existingRawMaterial.setName(rawMaterialDTO.getName());
        existingRawMaterial.setColor(rawMaterialDTO.getColor());
        existingRawMaterial.setQuantity(rawMaterialDTO.getQuantity());
        existingRawMaterial.setDescription(rawMaterialDTO.getDescription());

        RawMaterial updateRawMaterial = rawMaterialRepository.save(existingRawMaterial);
        return rawMaterialMapper.toDto(updateRawMaterial);
    }

    public void deleteRawMaterial(Long id){
        rawMaterialRepository.deleteById(id);
    }
}
