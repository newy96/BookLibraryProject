package com.book.library.BookLibrary.services;

import com.book.library.BookLibrary.DTOs.CategoryDTO;
import com.book.library.BookLibrary.DTOs.CategoryInputDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getAllCategoriesByBookName(String bookName);

    Optional<CategoryDTO> getCategoryById(Long id);

    CategoryDTO createCategory(CategoryInputDTO categoryDTO);

    CategoryDTO updateCategory(Long id, CategoryInputDTO categoryDTO);

    void deleteCategory(Long id);
}
