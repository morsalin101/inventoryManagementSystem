package com.inventoryManagementSystem.ims.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class CategoryController {

    @GetMapping("/category")
   
    public String showCategoryPage(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("pageTitle", "Category Page");
        return "category";
    }
    
}
