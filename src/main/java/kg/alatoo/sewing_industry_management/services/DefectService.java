package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import java.util.List;


public interface DefectService {
    List<DefectDTO> getAllDefects();
    DefectDTO getDefectById(Long id);
    DefectDTO createDefect(DefectDTO defectDTO);
    DefectDTO updateDefect(Long id, DefectDTO defectDTO);
    void deleteDefect(Long id);
}
