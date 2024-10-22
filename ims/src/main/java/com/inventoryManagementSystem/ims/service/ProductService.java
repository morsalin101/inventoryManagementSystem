package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.Product;
import com.inventoryManagementSystem.ims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Save Product with Image as byte[] (no file system storage)
    public Product saveProduct(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes()); // Save image as byte array in DB
        }
        return productRepository.save(product);
    }

    // Update Product with optional Image replacement
    public Product updateProduct(Long id, Product updatedProduct, MultipartFile imageFile) throws IOException {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setP_name(updatedProduct.getP_name());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setCategory(updatedProduct.getCategory());

            if (imageFile != null && !imageFile.isEmpty()) {
                product.setImage(imageFile.getBytes()); // Update image if provided
            }

            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
