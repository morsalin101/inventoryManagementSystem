package com.inventoryManagementSystem.ims.controller;


import java.util.Map;
import com.inventoryManagementSystem.ims.model.Category;
import com.inventoryManagementSystem.ims.service.CategoryService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;


@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String showCategoryPage(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("pageTitle", "Category Page");
        return "category";
    }

   @PostMapping("/save-category")
public ResponseEntity<Map<String, String>> saveCategory(@RequestBody Map<String, String> requestData) {
    String categoryName = requestData.get("c_name");

    // Validate category name
    if (categoryName == null || categoryName.isEmpty()) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Category name cannot be empty");
        return ResponseEntity.badRequest().body(response);
    }

    // Create and save the category with column name c_name
    Category category = new Category();
    category.setC_name(categoryName);  // Use setter for c_name column
    categoryService.saveCategory(category);

    // Response back to the client
    Map<String, String> response = new HashMap<>();
    response.put("message", "Category saved successfully");
    return ResponseEntity.ok(response);
}

@GetMapping("/get-category")
public ResponseEntity<Iterable<Category>> getAllCategories() {
    Iterable<Category> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
}

 @PutMapping("/update-category/{id}")
    public ResponseEntity<Map<String, String>> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        categoryService.updateCategory(id, categoryDetails);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Category updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Category deleted successfully");
        return ResponseEntity.ok(response);
    }

}
