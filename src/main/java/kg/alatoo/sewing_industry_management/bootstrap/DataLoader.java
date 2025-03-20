package kg.alatoo.sewing_industry_management.bootstrap;

import kg.alatoo.sewing_industry_management.entities.Defect;
import kg.alatoo.sewing_industry_management.entities.Product;
import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.entities.User;
import kg.alatoo.sewing_industry_management.enums.Role;
import kg.alatoo.sewing_industry_management.enums.Status;
import kg.alatoo.sewing_industry_management.repositories.DefectRepository;
import kg.alatoo.sewing_industry_management.repositories.ProductRepository;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import kg.alatoo.sewing_industry_management.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final DefectRepository defectRepository;

    public DataLoader(RawMaterialRepository rawMaterialRepository, ProductRepository productRepository, UserRepository userRepository, DefectRepository defectRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.defectRepository = defectRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("Aika");
        user.setPassword("admin123");
        user.setEmail("aika@example.com");
        user.setRole(Role.ADMIN);

        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("Bakyt");
        user1.setPassword("admin123");
        user1.setEmail("Bakyt1998@example.com");
        user1.setRole(Role.IRONER);
        userRepository.save(user);
        userRepository.save(user1);


        RawMaterial material1 = new RawMaterial();
        material1.setName("Cotton");
        material1.setColor("White");
        material1.setQuantity(100.0);
        material1.setStatus("IN PROCESS");

        RawMaterial material2 = new RawMaterial();
        material2.setName("Silk");
        material2.setColor("Red");
        material2.setQuantity(50.0);
        material2.setStatus("IN STOCK");
        rawMaterialRepository.save(material1);
        rawMaterialRepository.save(material2);

        Product product = new Product();
        product.setId(1L);
        product.setName("T-Shirt");
        product.setStyle("Casual");
        product.setColor("White");
        product.setSize("M");
        product.setQuantity(50);
        product.setStatus(Status.CUTTING);
        product.setRawMaterial(material1);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Dress");
        product2.setStyle("Casual");
        product2.setColor("Red");
        product2.setSize("M");
        product2.setQuantity(50);
        product2.setStatus(Status.SEWING);
        product2.setRawMaterial(material1);
        productRepository.save(product2);

        Defect defect = new Defect();
        defect.setId(1L);
        defect.setDescription("Small collar");
        defect.setQuantity(20);
        defect.setProduct(product);
        defectRepository.save(defect);
    }
}


