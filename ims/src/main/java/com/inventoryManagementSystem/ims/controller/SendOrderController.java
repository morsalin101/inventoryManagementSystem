package com.inventoryManagementSystem.ims.controller;

import com.inventoryManagementSystem.ims.model.Customer;
import com.inventoryManagementSystem.ims.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SendOrderController {
   

    @GetMapping("/sendorder")
    public String showCategoryPage(Model model) {
        model.addAttribute("title", "Order");
        model.addAttribute("pageTitle", "Send Page");
        return "sendorder";
    }
}