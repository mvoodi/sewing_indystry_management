package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.entities.Defect;
import kg.alatoo.sewing_industry_management.mappers.DefectMapper;
import kg.alatoo.sewing_industry_management.repositories.DefectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefectServiceImplTest {

    @Mock
    private DefectRepository defectRepository;

    @Mock
    private DefectMapper defectMapper;

    @InjectMocks
    private DefectServiceImpl defectService;

    private Defect defect;
    private DefectDTO defectDTO;

    @BeforeEach
    void setUp() {
        defect = new Defect();
        defect.setId(1L);
        defect.setDescription("Torn fabric");
        defect.setQuantity(5);

        defectDTO = new DefectDTO(1L, "Torn fabric", 5, 2L);
    }

    @Test
    void getAllDefects() {
        when(defectRepository.findAll()).thenReturn(Arrays.asList(defect));
        when(defectMapper.toDto(defect)).thenReturn(defectDTO);

        List<DefectDTO> defects = defectService.getAllDefects();

        assertNotNull(defects);
        assertEquals(1, defects.size());
        assertEquals("Torn fabric", defects.get(0).getDescription());
        verify(defectRepository, times(1)).findAll();
    }

    @Test
    void getDefectById() {
        when(defectRepository.findById(1L)).thenReturn(Optional.of(defect));
        when(defectMapper.toDto(defect)).thenReturn(defectDTO);

        DefectDTO foundDefect = defectService.getDefectById(1L);

        assertNotNull(foundDefect);
        assertEquals(1L, foundDefect.getId());
        assertEquals("Torn fabric", foundDefect.getDescription());
    }

    @Test
    void createDefect() {
        when(defectMapper.toEntity(defectDTO)).thenReturn(defect);
        when(defectRepository.save(defect)).thenReturn(defect);
        when(defectMapper.toDto(defect)).thenReturn(defectDTO);

        DefectDTO createdDefect = defectService.createDefect(defectDTO);

        assertNotNull(createdDefect);
        assertEquals("Torn fabric", createdDefect.getDescription());
        verify(defectRepository, times(1)).save(defect);
    }

    @Test
    void updateDefect() {
        when(defectRepository.findById(1L)).thenReturn(Optional.of(defect));
        when(defectRepository.save(defect)).thenReturn(defect);
        when(defectMapper.toDto(defect)).thenReturn(defectDTO);

        defectDTO.setDescription("Fixed fabric");
        DefectDTO updatedDefect = defectService.updateDefect(1L, defectDTO);

        assertNotNull(updatedDefect);
        assertEquals("Fixed fabric", updatedDefect.getDescription());
    }

    @Test
    void deleteDefect() {
        doNothing().when(defectRepository).deleteById(1L);

        defectService.deleteDefect(1L);

        verify(defectRepository, times(1)).deleteById(1L);
    }
}
