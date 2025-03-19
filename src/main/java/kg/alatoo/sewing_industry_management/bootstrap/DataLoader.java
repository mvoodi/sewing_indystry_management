package kg.alatoo.sewing_industry_management.bootstrap;

import kg.alatoo.sewing_industry_management.entities.RawMaterial;
import kg.alatoo.sewing_industry_management.repositories.RawMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final RawMaterialRepository rawMaterialRepository;

    public DataLoader(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        RawMaterial material1 = new RawMaterial();
        material1.setName("Cotton");
        material1.setColor("White");
        material1.setQuantity(100.0);
        material1.setStatus("High-quality cotton fabric");

        RawMaterial material2 = new RawMaterial();
        material2.setName("Silk");
        material2.setColor("Red");
        material2.setQuantity(50.0);
        material2.setStatus("Luxurious silk fabric");

        rawMaterialRepository.save(material1);
        rawMaterialRepository.save(material2);

    }
}


