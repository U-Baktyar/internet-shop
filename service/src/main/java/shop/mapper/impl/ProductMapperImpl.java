package shop.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.dto.ProductDto;
import shop.entity.product.Product;
import shop.mapper.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    public ProductMapperImpl() {}

    public ProductDto toDto(Product p) {
        ProductDto dto = new ProductDto();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        String unit = String.valueOf(p.getQuantity())+ " " + String.valueOf(p.getUnit());
        dto.setUnit(unit);
        return dto;
    }



}

