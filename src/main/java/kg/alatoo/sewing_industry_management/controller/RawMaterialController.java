package kg.alatoo.sewing_industry_management.controller;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.service.RawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;
    @GetMapping
    public List<RawMaterialDTO> getAllRawMaterials() {
        return rawMaterialService.getAllRawMaterials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> getRawMaterialById(@PathVariable Long id){
        RawMaterialDTO rawMaterialDTO = rawMaterialService.getRawMaterialById(id);
        return ResponseEntity.ok(rawMaterialDTO);
    }

    @PostMapping
    public ResponseEntity<RawMaterialDTO> createRawMaterial(@RequestBody RawMaterialDTO rawMaterialDTO){
        RawMaterialDTO createRawMaterial = rawMaterialService.createRawMaterial(rawMaterialDTO);
        return ResponseEntity.ok(createRawMaterial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialDTO> updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterialDTO rawMaterialDTO){
        RawMaterialDTO updateRawMaterial = rawMaterialService.updateRawMaterial(id, rawMaterialDTO);
        return ResponseEntity.ok(updateRawMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable Long id){
        rawMaterialService.deleteRawMaterial(id);
        return ResponseEntity.noContent().build();

    }
}
