package com.andrezktt.product_catalog.controllers;

import com.andrezktt.product_catalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Books"));
        categories.add(new Category(2L, "Electronics"));
        return ResponseEntity.ok().body(categories);
    }
}
