package com.andrezktt.product_catalog.repositories;

import com.andrezktt.product_catalog.tests.Factory;
import com.andrezktt.product_catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long wrongId;
    private Integer totalProducts;

    @BeforeEach
    void setUp () throws Exception {
        existingId = 1L;
        wrongId = 1000L;
        totalProducts = 25;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Assertions.assertTrue(repository.findById(existingId).isEmpty());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(totalProducts + 1, product.getId());
    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() {
        Assertions.assertTrue(repository.findById(existingId).isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyWhenIdDoesNotExists() {
        Assertions.assertTrue(repository.findById(wrongId).isEmpty());
    }
}
