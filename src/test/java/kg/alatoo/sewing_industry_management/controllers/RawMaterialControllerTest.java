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

@ExtendWith(MockitoExtension.class)  // Подключаем поддержку Mockito в JUnit 5
class RawMaterialControllerTest {

    @Mock
    private RawMaterialService rawMaterialService;  // Мок для RawMaterialService

    @InjectMocks
    private RawMaterialController rawMaterialController;  // Контроллер, в который будет внедряться мок

    private RawMaterialDTO rawMaterialDTO;

    @BeforeEach
    void setUp() {
        rawMaterialDTO = new RawMaterialDTO(1L, "Cotton", "White", 100.0, "High-quality cotton");  // Инициализируем объект RawMaterialDTO
    }

    @Test
    void getRawMaterialById() {
        // Arrange
        when(rawMaterialService.getRawMaterialById(1L)).thenReturn(rawMaterialDTO);  // Настроим мок для возвращаемого значения

        // Act
        ResponseEntity<RawMaterialDTO> result = rawMaterialController.getRawMaterialById(1L);  // Вызовем метод контроллера

        // Assert
        assertNotNull(result);  // Убедимся, что результат не равен null
        assertEquals(200, result.getStatusCodeValue());  // Проверим код статуса
        assertEquals("Cotton", result.getBody().getName());  // Проверим название сырья
    }

    @Test
    void createRawMaterial() {
        // Arrange
        when(rawMaterialService.createRawMaterial(rawMaterialDTO)).thenReturn(rawMaterialDTO);  // Настроим мок для возвращаемого значения

        // Act
        ResponseEntity<RawMaterialDTO> result = rawMaterialController.createRawMaterial(rawMaterialDTO);  // Вызовем метод контроллера

        // Assert
        assertNotNull(result);  // Убедимся, что результат не равен null
        assertEquals(200, result.getStatusCodeValue());  // Проверим код статуса
        assertEquals("Cotton", result.getBody().getName());  // Проверим название сырья
    }

    @Test
    void updateRawMaterial() {
        // Arrange
        when(rawMaterialService.updateRawMaterial(1L, rawMaterialDTO)).thenReturn(rawMaterialDTO);  // Настроим мок для возвращаемого значения

        // Act
        ResponseEntity<RawMaterialDTO> result = rawMaterialController.updateRawMaterial(1L, rawMaterialDTO);  // Вызовем метод контроллера

        // Assert
        assertNotNull(result);  // Убедимся, что результат не равен null
        assertEquals(200, result.getStatusCodeValue());  // Проверим код статуса
        assertEquals("Cotton", result.getBody().getName());  // Проверим название сырья
    }

    @Test
    void deleteRawMaterial() {
        // Arrange
        doNothing().when(rawMaterialService).deleteRawMaterial(1L);  // Настроим мок для удаления

        // Act
        ResponseEntity<Void> result = rawMaterialController.deleteRawMaterial(1L);  // Вызовем метод контроллера

        // Assert
        assertNotNull(result);  // Убедимся, что результат не равен null
        assertEquals(204, result.getStatusCodeValue());  // Проверим код статуса
        verify(rawMaterialService, times(1)).deleteRawMaterial(1L);  // Проверим, что метод deleteRawMaterial был вызван один раз
    }
}
