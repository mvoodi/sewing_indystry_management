package kg.alatoo.sewing_industry_management.controllers;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.services.DefectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/defects")
@AllArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @GetMapping
    public List<DefectDTO> getAllDefects(){
        return defectService.getAllDefects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefectDTO> getDefectById(@PathVariable Long id){
        DefectDTO defectDTO = defectService.getDefectById(id);
        return ResponseEntity.ok(defectDTO);

    }

    @PostMapping
    public ResponseEntity<DefectDTO> createDefect(@RequestBody DefectDTO defectDTO) {
        DefectDTO createdDefect = defectService.createDefect(defectDTO);
        return ResponseEntity.ok(createdDefect);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefectDTO> updateDefect(@PathVariable Long id, @RequestBody DefectDTO defectDTO) {
        DefectDTO updatedDefect = defectService.updateDefect(id, defectDTO);
        return ResponseEntity.ok(updatedDefect);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefect(@PathVariable Long id) {
        defectService.deleteDefect(id);
        return ResponseEntity.noContent().build();
    }
}
