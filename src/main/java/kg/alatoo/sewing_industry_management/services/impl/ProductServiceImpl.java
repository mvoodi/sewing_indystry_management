package kg.alatoo.sewing_industry_management.services.impl;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;
import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.enums.Status;
import kg.alatoo.sewing_industry_management.mappers.ProductMapper;
import kg.alatoo.sewing_industry_management.repositories.ProductRepository;
import kg.alatoo.sewing_industry_management.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setStyle(productDTO.getStyle());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setStatus(productDTO.getStatus());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public  void nextStep(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(next(product.getStatus()));
        productRepository.save(product);

    }

    private Status next(Status status){
        if (status == Status.INSTOCK){
            return Status.CUTTING;
        }
        else if(status == Status.CUTTING){
            return Status.SEWING;
        }
        else if(status == Status.SEWING){
            return Status.IRONING;
        }
        else if(status == Status.IRONING){
            return Status.PACKAGING;
        }
        else if(status == Status.PACKAGING){
            return Status.READY;
        }
        else{
            return status;
        }

    }

}
