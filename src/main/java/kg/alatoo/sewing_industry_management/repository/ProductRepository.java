package kg.alatoo.sewing_industry_management.repository;

import kg.alatoo.sewing_industry_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
