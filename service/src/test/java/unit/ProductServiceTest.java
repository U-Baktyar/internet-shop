package unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.configuration.AppConfig;
import shop.dao.ProductDao;
import shop.dto.ProductDto;
import shop.entity.product.Category;
import shop.entity.product.Product;
import shop.entity.product.UnitEnum;
import shop.mapper.ProductMapper;
import shop.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ProductServiceTest {

    @MockitoBean
    private ProductDao productDao;

    @MockitoBean
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Test
    void testGetProductsByCategoryName() {
        String categoryName = "Одежда";
        List<Product> products = getProducts();
        List<ProductDto> productDtoList = createProducts();

        // Мокаем DAO
        when(productDao.findByCategoryName(categoryName)).thenReturn(products);

        // Мокаем Mapper
        for (int i = 0; i < products.size(); i++) {
            when(productMapper.toDto(products.get(i))).thenReturn(productDtoList.get(i));
        }

        // Вызываем сервис
        List<ProductDto> result = productService.finByCategoryName(categoryName);

    }

    // DTO должны содержать строку unit вида "10 ШТ"
    public static List<ProductDto> createProducts() {
        List<ProductDto> products = new ArrayList<>();

        ProductDto p1 = new ProductDto();
        p1.setName("Смартфон");
        p1.setPrice(new BigDecimal("499.99"));
        p1.setUnit("10 ШТ");
        products.add(p1);

        ProductDto p2 = new ProductDto();
        p2.setName("Ноутбук");
        p2.setPrice(new BigDecimal("999.50"));
        p2.setUnit("5 ШТ");
        products.add(p2);

        ProductDto p3 = new ProductDto();
        p3.setName("Футболка");
        p3.setPrice(new BigDecimal("19.99"));
        p3.setUnit("50 ШТ");
        products.add(p3);

        ProductDto p4 = new ProductDto();
        p4.setName("Джинсы");
        p4.setPrice(new BigDecimal("39.99"));
        p4.setUnit("30 ШТ");
        products.add(p4);

        return products;
    }

    public static List<Product> getProducts() {
        Category electronics = new Category();
        electronics.setName("Электроника");

        Category clothing = new Category();
        clothing.setName("Одежда");

        Product p1 = new Product();
        p1.setName("Смартфон");
        p1.setPrice(new BigDecimal("499.99"));
        p1.setQuantity(10);
        p1.setUnit(UnitEnum.ШТ);
        p1.setCategory(electronics);

        Product p2 = new Product();
        p2.setName("Ноутбук");
        p2.setPrice(new BigDecimal("999.50"));
        p2.setQuantity(5);
        p2.setUnit(UnitEnum.ШТ);
        p2.setCategory(electronics);

        Product p3 = new Product();
        p3.setName("Футболка");
        p3.setPrice(new BigDecimal("19.99"));
        p3.setQuantity(50);
        p3.setUnit(UnitEnum.ШТ);
        p3.setCategory(clothing);

        Product p4 = new Product();
        p4.setName("Джинсы");
        p4.setPrice(new BigDecimal("39.99"));
        p4.setQuantity(30);
        p4.setUnit(UnitEnum.ШТ);
        p4.setCategory(clothing);

        return List.of(p1, p2, p3, p4);
    }
}
