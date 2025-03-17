package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.entities.Defect;
import kg.alatoo.sewing_industry_management.mappers.DefectMapper;
import kg.alatoo.sewing_industry_management.repositories.DefectRepository;
import lombok.AllArgsConstructor;
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
                .orElseThrow(() -> new RuntimeException("Defect not found"));
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
                .orElseThrow(() -> new RuntimeException("Defect not found"));

        existingDefect.setDescription(defectDTO.getDescription());
        existingDefect.setQuantity(defectDTO.getQuantity());

        Defect updatedDefect = defectRepository.save(existingDefect);
        return defectMapper.toDto(updatedDefect);
    }

    @Override
    public void deleteDefect(Long id) {
        defectRepository.deleteById(id);
    }
}
