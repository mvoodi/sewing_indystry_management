package kg.alatoo.sewing_industry_management.services;

import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.repositories.ProductRepository;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    private RawMaterialRepository rawMaterialRepository;
    public Product createProduct(Product product,Long rawMaterialId){
        RawMaterial rawMaterial = rawMaterialRepository.findById((rawMaterialId))
                .orElseThrow(() -> new RuntimeException("RawMaterial not found"));

        product.setColor(rawMaterial.getColor());
        product.setRawMaterial((rawMaterial));

        return productRepository.save(product);
    }
}
