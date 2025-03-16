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
public class DefectService {
    private final DefectMapper defectMapper;
    private final DefectRepository defectRepository;

    public List<DefectDTO> getAllDefects(){
        return defectRepository.findAll()
                .stream()
                .map(defectMapper::toDto)
                .collect(Collectors.toList());
    }

    public DefectDTO getById(Long id){
        Defect defect = defectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Defect not found"));
        return defectMapper.toDto(defect);
    }

    public DefectDTO createDefect(DefectDTO defectDTO){
        Defect defect = defectMapper.toEntity(defectDTO);
        Defect savedDefect = defectRepository.save(defect);
        return defectMapper.toDto(savedDefect);

    }

    public DefectDTO updateDefect(Long id, DefectDTO defectDTO){
        Defect existingDefect = defectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Defect not found"));

        existingDefect.setDescription(defectDTO.getDescription());
        existingDefect.setQuantity(defectDTO.getQuantity());

        Defect updatedDefect = defectRepository.save(existingDefect);
        return defectMapper.toDto(updatedDefect);

    }

    public void deleteDefect(Long id){
        defectRepository.deleteById(id);
    }
}
