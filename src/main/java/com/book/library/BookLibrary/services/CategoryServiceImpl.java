package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.entities.Category;
import com.book.library.BookLibrary.repositories.CategoryRepository;
import com.book.library.BookLibrary.Mapper.Mapper;
import com.book.library.BookLibrary.DTOs.CategoryDTO;
import com.book.library.BookLibrary.DTOs.CategoryInputDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return mapper.mapCategories(categories);
    }

    public List<CategoryDTO> getAllCategoriesByBookName(String bookName) {
        List<Category> categories = categoryRepository.findAllByBookName(bookName);
        return mapper.mapCategories(categories);
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        CategoryDTO categoryDTO = mapper.modelMapper.map(category.orElse(null), CategoryDTO.class);
        return Optional.ofNullable(categoryDTO);
    }

    public CategoryDTO createCategory(CategoryInputDTO categoryDTO) {
        Category categoryToSave = mapper.modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(categoryToSave);
        return mapper.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(Long id, CategoryInputDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingCategory.setName(categoryDTO.getName());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapper.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    public void deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        categoryRepository.deleteById(existingCategory.getId());
    }
}
