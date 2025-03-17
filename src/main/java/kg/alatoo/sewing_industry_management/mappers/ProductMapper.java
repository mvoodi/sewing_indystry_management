package kg.alatoo.sewing_industry_management.mappers;

import kg.alatoo.sewing_industry_management.dto.ProductDTO;
import kg.alatoo.sewing_industry_management.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
