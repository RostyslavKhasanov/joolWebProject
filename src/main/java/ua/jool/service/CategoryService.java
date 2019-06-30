package ua.jool.service;

import ua.jool.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {

    void create(CategoryDTO categoryDTO);

    List<CategoryDTO> findAll();

    void deleteCategoryById(Long id);

}
