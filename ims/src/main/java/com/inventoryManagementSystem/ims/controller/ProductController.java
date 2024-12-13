package com.inventoryManagementSystem.ims.controller;

import com.inventoryManagementSystem.ims.model.Product;
import com.inventoryManagementSystem.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products") // Base path for all product-related routes
public class ProductController {

    @Autowired
    private ProductService productService;

    // Display the product page
    @GetMapping("/all-products")
    public String getProductPage(Model model) {
        // Optional: If you want to load existing products when displaying the product page
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products); // Add products to the model for Thymeleaf rendering
        model.addAttribute("title", "Product");
        model.addAttribute("pageTitle", "Product Page");
        return "get_product"; // This will return product.html
    }
     
    @GetMapping("/add-products")
    public String getAddProductPage(Model model) {
        model.addAttribute("title", "Add Product");
        model.addAttribute("pageTitle", "Add New Product");
        return "add_product"; // Renders add_product.html for adding a new product
    }

     // Get a single product by ID
     @GetMapping("/get/{id}")
     @ResponseBody
     public ResponseEntity<Product> getProductById(@PathVariable Long id) {
         Optional<Product> productOptional = productService.getProductById(id);
         if (productOptional.isPresent()) {
             Product product = productOptional.get();
             return ResponseEntity.ok(product);
         } else {
             return ResponseEntity.notFound().build();
         }
     }

    // Get all products for API
    @GetMapping("/get") // Renamed to avoid confusion with the HTML endpoint
    @ResponseBody // This indicates that the response should be directly written to the response body
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    // Save a new product
    @PostMapping("/save")
    public String saveProduct(
            @RequestParam("p_name") String name,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("category-id") Long categoryId,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            // Create and populate the Product object
            Product product = new Product();
            product.setP_name(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setCategory(String.valueOf(categoryId)); // Adjust if category is not a string
    
            // Process the image file
            if (image != null && !image.isEmpty()) {
                product.setImage(image.getBytes());
            }
    
            // Save the product
            productService.saveProduct(product, image);
    
            // Redirect to the product list page after successful save
            return "redirect:/products/all-products";
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // Return an error page in case of failure
        }
    }
    

    // Update an existing product by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, 
            @RequestPart("product") Product updatedProduct, 
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            Product updated = productService.updateProduct(id, updatedProduct, imageFile);
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
