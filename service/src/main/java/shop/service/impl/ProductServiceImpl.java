package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.ProductDao;
import shop.dto.ProductDto;

import shop.mapper.ProductMapper;
import shop.mapper.impl.ProductMapperImpl;
import shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductMapper productMapper) {
        this.productDao = productDao;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() {
        return productDao.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findByName(String name) {
        return productMapper.toDto(productDao.findByName(name));
    }

    public List<ProductDto> finByCategoryName(String categoryName){
        return productDao.findByCategoryName(categoryName)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

}
