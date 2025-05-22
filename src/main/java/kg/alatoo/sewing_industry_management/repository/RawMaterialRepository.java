package kg.alatoo.sewing_industry_management.repository;

import kg.alatoo.sewing_industry_management.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
