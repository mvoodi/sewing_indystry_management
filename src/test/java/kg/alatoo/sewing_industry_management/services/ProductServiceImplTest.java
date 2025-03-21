package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;
import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.enums.Status;
import kg.alatoo.sewing_industry_management.mappers.ProductMapper;
import kg.alatoo.sewing_industry_management.repositories.ProductRepository;
import kg.alatoo.sewing_industry_management.services.impl.ProductServiceImpl;
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
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        RawMaterial rawMaterial = new RawMaterial();
        product = new Product();
        product.setId(1L);
        product.setName("Shirt");
        product.setStyle("Casual");
        product.setColor("Blue");
        product.setSize("M");
        product.setQuantity(100);
        product.setStatus(Status.INSTOCK);
        product.setRawMaterial(rawMaterial);

        productDTO = new ProductDTO(1L, "Shirt", "Casual", "Blue", "M", 100, Status.INSTOCK,1L);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        List<ProductDTO> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Shirt", products.get(0).getName());
    }

    @Test
    void getProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals("Shirt", foundProduct.getName());
    }

    @Test
    void createProduct() {
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals("Shirt", createdProduct.getName());
    }

    @Test
    void updateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals("Shirt", updatedProduct.getName());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        assertDoesNotThrow(() -> productService.deleteProduct(1L));

        verify(productRepository, times(1)).deleteById(1L);
    }
}
