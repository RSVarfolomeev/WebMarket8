package ru.geekbrains.aprilmarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.aprilmarket.beans.SomeBean;
import ru.geekbrains.aprilmarket.services.CartItemService;

@SpringBootTest(classes = {SomeBean.class/*, CartItemService.class*/})
@ActiveProfiles("test")
public class SomeBeanTest {

    @Autowired
    private SomeBean bean;

    @Test
    public void incrementTest() {
        bean.increment();
        bean.increment();
        bean.increment();

        Assertions.assertEquals(3, bean.getValue());
    }
}
