package kg.alatoo.sewing_industry_management.controllers;

import kg.alatoo.sewing_industry_management.dto.RawMaterialDTO;
import kg.alatoo.sewing_industry_management.services.RawMaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RawMaterialControllerTest {

    @Mock
    private RawMaterialService rawMaterialService;

    @InjectMocks
    private RawMaterialController rawMaterialController;

    private RawMaterialDTO rawMaterialDTO;

    @BeforeEach
    void setUp() {
        rawMaterialDTO = new RawMaterialDTO(1L, "Cotton", "White", 100.0, "High-quality cotton");
    }

    @Test
    void getRawMaterialById() {
        when(rawMaterialService.getRawMaterialById(1L)).thenReturn(rawMaterialDTO);

        ResponseEntity<RawMaterialDTO> result = rawMaterialController.getRawMaterialById(1L);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Cotton", result.getBody().getName());
    }

    @Test
    void createRawMaterial() {
        when(rawMaterialService.createRawMaterial(rawMaterialDTO)).thenReturn(rawMaterialDTO);

        ResponseEntity<RawMaterialDTO> result = rawMaterialController.createRawMaterial(rawMaterialDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Cotton", result.getBody().getName());
    }

    @Test
    void updateRawMaterial() {
        when(rawMaterialService.updateRawMaterial(1L, rawMaterialDTO)).thenReturn(rawMaterialDTO);

        ResponseEntity<RawMaterialDTO> result = rawMaterialController.updateRawMaterial(1L, rawMaterialDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Cotton", result.getBody().getName());
    }

    @Test
    void deleteRawMaterial() {
        doNothing().when(rawMaterialService).deleteRawMaterial(1L);

        ResponseEntity<Void> result = rawMaterialController.deleteRawMaterial(1L);

        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(rawMaterialService, times(1)).deleteRawMaterial(1L);
    }
}