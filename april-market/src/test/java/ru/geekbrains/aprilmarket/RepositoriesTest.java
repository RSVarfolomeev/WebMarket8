package ru.geekbrains.aprilmarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.aprilmarket.entities.Product;
import ru.geekbrains.aprilmarket.repositories.ProductRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Order(-100)
    public void productRepositoryTest() {
        Product product = new Product();
        product.setTitle("GB");
        entityManager.persist(product);
        entityManager.flush();

        List<Product> productList = productRepository.findAll();

        Assertions.assertEquals(4, productList.size());
        Assertions.assertEquals("GB", productList.get(3).getTitle());
    }

    @Test
    @Order(Integer.MAX_VALUE)
    public void initDbTest() {
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(3, products.size());
    }
}
