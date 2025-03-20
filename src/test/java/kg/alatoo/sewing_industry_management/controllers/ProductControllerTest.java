package kg.alatoo.sewing_industry_management.controllers;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;
import kg.alatoo.sewing_industry_management.enums.Status;
import kg.alatoo.sewing_industry_management.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO(1L, "T-shirt", "Casual", "Blue", "M", 100, Status.INSTOCK, 1L);
    }

    @Test
    void getAllProducts() {
        List<ProductDTO> products = Arrays.asList(productDTO);
        when(productService.getAllProducts()).thenReturn(products);

        List<ProductDTO> result = productController.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("T-shirt", result.get(0).getName());
    }

    @Test
    void getProductById() {
        when(productService.getProductById(1L)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> result = productController.getProductById(1L);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("T-shirt", result.getBody().getName());
    }

    @Test
    void createProduct() {
        when(productService.createProduct(productDTO)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> result = productController.createProduct(productDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("T-shirt", result.getBody().getName());
    }

    @Test
    void updateProduct() {
        when(productService.updateProduct(1L, productDTO)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> result = productController.updateProduct(1L, productDTO);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("T-shirt", result.getBody().getName());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Void> result = productController.deleteProduct(1L);

        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(productService, times(1)).deleteProduct(1L);
    }
}
