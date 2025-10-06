package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import shop.entity.product.Product;

import java.util.List;

@Component
public interface ProductDao extends JpaRepository<Product, Long> {

    Product findByName(String name);

    List<Product> findByCategoryName(String categoryName);

}
