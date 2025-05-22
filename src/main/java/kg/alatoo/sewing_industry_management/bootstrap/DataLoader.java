package kg.alatoo.sewing_industry_management.bootstrap;

import kg.alatoo.sewing_industry_management.model.*;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.enums.Status;
import kg.alatoo.sewing_industry_management.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final RawMaterialRepository rawRepo;
    private final ProductRepository     productRepo;
    private final UserRepository        userRepo;
    private final DefectRepository      defectRepo;

    @Bean
    @Transactional
    public ApplicationRunner loadDemoData() {
        return args -> {
            if (userRepo.count() > 0) return;   // данные уже есть

            /* ───── USERS ───── */
            User admin = new User();
            admin.setUsername("Aika");
            admin.setPassword("aikaa123");    // только для демо!
            admin.setEmail("aika@example.com");
            admin.setRole(Role.ADMIN);
            userRepo.save(admin);

            User manager = new User();
            manager.setUsername("Bakyt");
            manager.setPassword("{noop}pass");
            manager.setEmail("bakyt1998@example.com");
            manager.setRole(Role.MANAGER);
            userRepo.save(manager);

            /* ───── RAW MATERIALS ───── */
            RawMaterial cotton = new RawMaterial();
            cotton.setName("Cotton");
            cotton.setColor("White");
            cotton.setQuantity(100.0);
            cotton.setStatus("IN PROCESS");
            rawRepo.save(cotton);

            RawMaterial silk = new RawMaterial();
            silk.setName("Silk");
            silk.setColor("Red");
            silk.setQuantity(50.0);
            silk.setStatus("IN STOCK");
            rawRepo.save(silk);

            /* ───── PRODUCTS ───── */
            Product tshirt = new Product();
            tshirt.setName("T‑Shirt");
            tshirt.setStyle("Casual");
            tshirt.setColor("White");
            tshirt.setSize("M");
            tshirt.setQuantity(50);
            tshirt.setStatus(Status.CUTTING);
            tshirt.setRawMaterial(cotton);
            productRepo.save(tshirt);

            Product dress = new Product();
            dress.setName("Dress");
            dress.setStyle("Casual");
            dress.setColor("Red");
            dress.setSize("M");
            dress.setQuantity(50);
            dress.setStatus(Status.SEWING);
            dress.setRawMaterial(cotton);
            productRepo.save(dress);

            /* ───── DEFECTS ───── */
            Defect defect = new Defect();
            defect.setDescription("Small collar");
            defect.setQuantity(20);
            defect.setProduct(tshirt);
            defectRepo.save(defect);
        };
    }
}
