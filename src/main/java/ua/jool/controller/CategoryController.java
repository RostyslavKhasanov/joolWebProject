package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jool.domain.CategoryDTO;
import ua.jool.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        return new ResponseEntity<List<CategoryDTO>>(categoryDTOS, HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> deleteGame(
            @PathVariable("categoryId") Long id
    ) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
