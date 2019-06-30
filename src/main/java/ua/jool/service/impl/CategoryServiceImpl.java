package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jool.domain.CategoryDTO;
import ua.jool.entity.CategoryEntity;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.CategoryRepository;
import ua.jool.service.CategoryService;
import ua.jool.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = modelMapper.mapAll(categoryEntities, CategoryDTO.class);
        return categoryDTOS;
    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Could not delete product with id[" + id + "]. Not found")
        );
        categoryRepository.deleteById(id);
    }
}
