package ru.geekbrains.aprilmarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.aprilmarket.entities.Product;
import ru.geekbrains.aprilmarket.repositories.ProductRepository;
import ru.geekbrains.aprilmarket.services.ProductService;


import java.math.BigDecimal;
import java.util.Optional;

//@SpringBootTest(classes = {ProductRepository.class, ProductService.class})
@SpringBootTest(classes = ProductService.class)
//@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProduct() {
        Product demoProduct = new Product();
        demoProduct.setTitle("Snickers");
        demoProduct.setPrice(new BigDecimal("50.0"));
        demoProduct.setId(10L);

        Mockito
                .doReturn(Optional.of(demoProduct))
                .when(productRepository)
                .findById(10L);

        Product p = productService.findProductById(10L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
        Assertions.assertEquals("Snickers", p.getTitle());

//        Product p = productService.findProductById(2L).get();
//        Assertions.assertEquals("Bread", p.getTitle());
    }
}
