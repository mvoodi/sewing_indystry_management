package kg.alatoo.sewing_industry_management.controller;

import kg.alatoo.sewing_industry_management.dto.DefectDTO;
import kg.alatoo.sewing_industry_management.service.DefectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class DefectControllerTest {

    @Mock
    private DefectService defectService;

    @InjectMocks
    private DefectController defectController;

    private DefectDTO defectDTO;

    @BeforeEach
    void setUp() {
        defectDTO = new DefectDTO(1L, "Broken Stitch", 5, 1L);
    }

    @Test
    void getAllDefects() {
        when(defectService.getAllDefects()).thenReturn(Arrays.asList(defectDTO));

        var result = defectController.getAllDefects();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Broken Stitch", result.get(0).getDescription());
    }

    @Test
    void getDefectById() {
        when(defectService.getDefectById(1L)).thenReturn(defectDTO);

        ResponseEntity<DefectDTO> result = defectController.getDefectById(1L);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Broken Stitch", result.getBody().getDescription());
    }

    @Test
    void createDefect() {
        when(defectService.createDefect(defectDTO)).thenReturn(defectDTO);

        ResponseEntity<DefectDTO> result = defectController.createDefect(defectDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Broken Stitch", result.getBody().getDescription());
    }

    @Test
    void updateDefect() {
        when(defectService.updateDefect(1L, defectDTO)).thenReturn(defectDTO);

        ResponseEntity<DefectDTO> result = defectController.updateDefect(1L, defectDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Broken Stitch", result.getBody().getDescription());
    }

    @Test
    void deleteDefect() {
        doNothing().when(defectService).deleteDefect(1L);

        ResponseEntity<Void> result = defectController.deleteDefect(1L);

        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(defectService, times(1)).deleteDefect(1L);
    }
}
