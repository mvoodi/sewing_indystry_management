package kg.alatoo.sewing_industry_management.service.impl;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.model.RawMaterial;
import kg.alatoo.sewing_industry_management.exception.RawMaterialNotFoundException;
import kg.alatoo.sewing_industry_management.mappers.RawMaterialMapper;
import kg.alatoo.sewing_industry_management.repository.RawMaterialRepository;
import kg.alatoo.sewing_industry_management.service.RawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;
    private final RawMaterialMapper rawMaterialMapper;

    @Override
    public List<RawMaterialDTO> getAllRawMaterials() {
        return rawMaterialRepository.findAll()
                .stream()
                .map(rawMaterialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RawMaterialDTO getRawMaterialById(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RawMaterialNotFoundException("Raw material with ID " + id + " not found"));
        return rawMaterialMapper.toDto(rawMaterial);
    }

    @Override
    public RawMaterialDTO createRawMaterial(RawMaterialDTO rawMaterialDTO) {
        RawMaterial rawMaterial = rawMaterialMapper.toEntity(rawMaterialDTO);
        RawMaterial savedRawMaterial = rawMaterialRepository.save(rawMaterial);
        return rawMaterialMapper.toDto(savedRawMaterial);
    }

    @Override
    public RawMaterialDTO updateRawMaterial(Long id, RawMaterialDTO rawMaterialDTO) {
        RawMaterial existingRawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RawMaterialNotFoundException("Raw material with ID " + id + " not found"));

        existingRawMaterial.setName(rawMaterialDTO.getName());
        existingRawMaterial.setColor(rawMaterialDTO.getColor());
        existingRawMaterial.setQuantity(rawMaterialDTO.getQuantity());
        existingRawMaterial.setStatus(rawMaterialDTO.getStatus());

        RawMaterial updatedRawMaterial = rawMaterialRepository.save(existingRawMaterial);
        return rawMaterialMapper.toDto(updatedRawMaterial);
    }

    @Override
    public void deleteRawMaterial(Long id) {
        if (!rawMaterialRepository.existsById(id)) {
            throw new RawMaterialNotFoundException("User with ID " + id + " not found");
        }
        rawMaterialRepository.deleteById(id);

    }
}

