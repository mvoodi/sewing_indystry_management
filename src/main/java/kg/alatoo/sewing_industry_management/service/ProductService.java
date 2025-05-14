package kg.alatoo.sewing_industry_management.service;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;

import java.util.List;


public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

    void nextStep(Long id);
}