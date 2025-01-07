package com.andrezktt.product_catalog.services;

import com.andrezktt.product_catalog.dto.CategoryDTO;
import com.andrezktt.product_catalog.entities.Category;
import com.andrezktt.product_catalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return repository.findAll().stream().map(CategoryDTO::new).toList();
    }
}
