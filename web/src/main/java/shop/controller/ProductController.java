package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import shop.dto.ProductDto;
import shop.service.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/product")
    public ProductDto getProductByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/category")
    public List<ProductDto> getProductsByCategory(@RequestParam("category") String category) {
        return productService.finByCategoryName(category);
    }
}
