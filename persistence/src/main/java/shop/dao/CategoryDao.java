package shop.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shop.entity.product.Category;

import java.util.Set;

public interface CategoryDao extends CrudRepository<Category, Integer> {

    @Query("SELECT c.name FROM Category c")
    Set<String> findAllNames();
}
