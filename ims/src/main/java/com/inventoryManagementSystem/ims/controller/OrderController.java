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
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showOrderPage(Model model) {
        model.addAttribute("title", "Order");
        model.addAttribute("pageTitle", "Order Page");
        return "order";  // Name of your Thymeleaf template for order management
    }

    // Get all orders
    @GetMapping("/get")
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Add a new order
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order createdOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Get order by ID for editing
    @GetMapping("/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    // Update an existing order
    @PutMapping("/update/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{orderId}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
