package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.AlbumImg;
import com.genesiscreations.kdec.model.Category;
import com.genesiscreations.kdec.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories() {
        return  ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PostMapping("/category/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryRepository.save(category));
    }
}
