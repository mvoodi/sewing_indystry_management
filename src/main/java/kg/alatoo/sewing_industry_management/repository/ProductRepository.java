package kg.alatoo.sewing_industry_management.repository;

import kg.alatoo.sewing_industry_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
