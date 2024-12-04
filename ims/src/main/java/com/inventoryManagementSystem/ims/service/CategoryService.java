package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.Category;
import com.inventoryManagementSystem.ims.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public void updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setC_name(categoryDetails.getC_name()); // Set updated name
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
    public long countCategories() {
        return categoryRepository.count();
    }
}
