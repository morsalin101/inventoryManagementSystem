package com.inventoryManagementSystem.ims.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product")
    public String getProductPage() {
        return "product"; // This will return product.html
    }
}
