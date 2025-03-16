package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;
import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.mappers.ProductMapper;
import kg.alatoo.sewing_industry_management.repositories.ProductRepository;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private RawMaterialRepository rawMaterialRepository;
    public Product createProduct(Product product,Long rawMaterialId){
        RawMaterial rawMaterial = rawMaterialRepository.findById((rawMaterialId))
                .orElseThrow(() -> new RuntimeException("RawMaterial not found"));

        product.setColor(rawMaterial.getColor());
        product.setRawMaterial((rawMaterial));

        return productRepository.save(product);
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDto(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setStyle(productDTO.getStyle());
        existingProduct.setColor(productDTO.getColor());
        existingProduct.setSize(productDTO.getSize());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setStatus(productDTO.getStatus());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
