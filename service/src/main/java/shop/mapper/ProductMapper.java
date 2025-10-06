package shop.mapper;

import shop.dto.ProductDto;
import shop.entity.product.Product;

public interface ProductMapper {
    ProductDto toDto(Product p);
}
