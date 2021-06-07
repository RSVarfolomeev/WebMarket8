package ru.geekbrains.aprilmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.dto.ProductDto;
import ru.geekbrains.aprilmarket.entities.Product;
import ru.geekbrains.aprilmarket.exception.ResourceNotFoundException;
import ru.geekbrains.aprilmarket.services.ProductService;
import ru.geekbrains.aprilmarket.specifications.ProductSpecifications;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {
    private ProductService productService;

//    @GetMapping
//    public List<ProductDto> findAllProducts() {
//        return productService.findAll();
//    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Not found!!!"));
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecifications.build(params), page, 4);
    }
}
