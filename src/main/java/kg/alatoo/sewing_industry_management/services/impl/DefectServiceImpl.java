package kg.alatoo.sewing_industry_management.services.impl;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.dto.UserDTO;
import kg.alatoo.sewing_industry_management.entities.Defect;
import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.entities.User;
import kg.alatoo.sewing_industry_management.exception.DefectNotFoundException;
import kg.alatoo.sewing_industry_management.exception.ProductNotFoundException;
import kg.alatoo.sewing_industry_management.exception.UserNotFoundException;
import kg.alatoo.sewing_industry_management.mappers.DefectMapper;
import kg.alatoo.sewing_industry_management.repositories.DefectRepository;
import kg.alatoo.sewing_industry_management.services.DefectService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;

    @Override
    public List<DefectDTO> getAllDefects() {
        return defectRepository.findAll()
                .stream()
                .map(defectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DefectDTO getDefectById(Long id) {
        Defect defect = defectRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Defect with ID " + id + " not found"));
        return defectMapper.toDto(defect);
    }


    @Override
    public DefectDTO createDefect(DefectDTO defectDTO) {
        Defect defect = defectMapper.toEntity(defectDTO);
        Defect savedDefect = defectRepository.save(defect);
        return defectMapper.toDto(savedDefect);
    }

    @Override
    public DefectDTO updateDefect(Long id, DefectDTO defectDTO) {
        Defect existingDefect = defectRepository.findById(id)
                .orElseThrow(() -> new DefectNotFoundException("Defect with ID " + id + " not found"));

        existingDefect.setDescription(defectDTO.getDescription());
        existingDefect.setQuantity(defectDTO.getQuantity());

        Defect updatedDefect = defectRepository.save(existingDefect);
        return defectMapper.toDto(updatedDefect);
    }

    @Override
    public void deleteDefect(Long id) {
        if (!defectRepository.existsById(id)) {
            throw new UserNotFoundException("Defect with ID " + id + " not found");
        }
        defectRepository.deleteById(id);
    }
}
