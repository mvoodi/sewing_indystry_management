package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.mappers.RawMaterialMapper;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RawMaterialServiceImplTest {

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @Mock
    private RawMaterialMapper rawMaterialMapper;

    @InjectMocks
    private RawMaterialServiceImpl rawMaterialService;

    private RawMaterial rawMaterial;
    private RawMaterialDTO rawMaterialDTO;

    @BeforeEach
    void setUp() {
        rawMaterial = new RawMaterial();
        rawMaterial.setId(1L);
        rawMaterial.setName("Cotton");
        rawMaterial.setColor("White");
        rawMaterial.setQuantity(100.5);
        rawMaterial.setDescription("High-quality cotton fabric");

        rawMaterialDTO = new RawMaterialDTO();
        rawMaterialDTO.setId(1L);
        rawMaterialDTO.setName("Cotton");
        rawMaterialDTO.setColor("White");
        rawMaterialDTO.setQuantity(100.5);
        rawMaterialDTO.setDescription("High-quality cotton fabric");
    }

    @Test
    void getAllRawMaterials() {
        when(rawMaterialRepository.findAll()).thenReturn(List.of(rawMaterial));
        when(rawMaterialMapper.toDto(rawMaterial)).thenReturn(rawMaterialDTO);

        List<RawMaterialDTO> materials = rawMaterialService.getAllRawMaterials();
        assertEquals(1, materials.size());
        assertEquals("Cotton", materials.get(0).getName());
    }

    @Test
    void getRawMaterialById() {
        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(rawMaterial));
        when(rawMaterialMapper.toDto(rawMaterial)).thenReturn(rawMaterialDTO);

        RawMaterialDTO foundMaterial = rawMaterialService.getRawMaterialById(1L);
        assertNotNull(foundMaterial);
        assertEquals("Cotton", foundMaterial.getName());
    }

    @Test
    void createRawMaterial() {
        when(rawMaterialMapper.toEntity(rawMaterialDTO)).thenReturn(rawMaterial);
        when(rawMaterialRepository.save(rawMaterial)).thenReturn(rawMaterial);
        when(rawMaterialMapper.toDto(rawMaterial)).thenReturn(rawMaterialDTO);

        RawMaterialDTO createdMaterial = rawMaterialService.createRawMaterial(rawMaterialDTO);
        assertNotNull(createdMaterial);
        assertEquals("Cotton", createdMaterial.getName());
    }

    @Test
    void updateRawMaterial() {
        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(rawMaterial));
        when(rawMaterialRepository.save(rawMaterial)).thenReturn(rawMaterial);
        when(rawMaterialMapper.toDto(rawMaterial)).thenReturn(rawMaterialDTO);

        RawMaterialDTO updatedMaterial = rawMaterialService.updateRawMaterial(1L, rawMaterialDTO);
        assertNotNull(updatedMaterial);
        assertEquals("Cotton", updatedMaterial.getName());
    }

    @Test
    void deleteRawMaterial() {
        doNothing().when(rawMaterialRepository).deleteById(1L);
        assertDoesNotThrow(() -> rawMaterialService.deleteRawMaterial(1L));
        verify(rawMaterialRepository, times(1)).deleteById(1L);
    }
}
