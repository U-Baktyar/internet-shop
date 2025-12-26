package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.entity.product.Category;

import java.util.Set;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    
    @Query("SELECT c.name FROM Category c")
    Set<String> findAllNames();
}
