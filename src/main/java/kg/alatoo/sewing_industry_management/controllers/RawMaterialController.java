package kg.alatoo.sewing_industry_management.controllers;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.services.RawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

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
