package shop.service;


import shop.dto.ProductDto;

import java.util.List;


public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findByName(String name);
    List<ProductDto> finByCategoryName(String categoryName);

}
