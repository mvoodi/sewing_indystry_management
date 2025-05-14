package kg.alatoo.sewing_industry_management.repository;

import kg.alatoo.sewing_industry_management.model.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
}
