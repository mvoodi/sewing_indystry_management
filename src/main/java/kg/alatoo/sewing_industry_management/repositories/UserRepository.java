package kg.alatoo.sewing_industry_management.repositories;

import kg.alatoo.sewing_industry_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
