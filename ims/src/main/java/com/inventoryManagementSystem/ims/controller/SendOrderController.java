package com.inventoryManagementSystem.ims.controller;


import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class SendOrderController {
   

    @Autowired
    private OrderService orderService;

    @GetMapping("/sendorder")
    public String showCategoryPage(Model model) {
        model.addAttribute("title", "Order");
        model.addAttribute("pageTitle", "Send Page");
        return "sendorder";
    }

   

    @PostMapping("/orders/save")
    public ResponseEntity<String> saveOrders(@RequestBody List<Order> orders) {
        // Set specific defaults if needed
        orders.forEach(order -> {
            if (order.getCustomer_name() == null) {
                order.setCustomer_name("Unknown Customer");
            }
            // Set other default values if necessary
        });

        orderService.saveOrders(orders);
        return ResponseEntity.ok("Invoice saved successfully!");
    }


}