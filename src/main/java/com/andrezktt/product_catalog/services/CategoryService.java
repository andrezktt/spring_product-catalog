package com.andrezktt.product_catalog.services;

import com.andrezktt.product_catalog.entities.Category;
import com.andrezktt.product_catalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }
}
