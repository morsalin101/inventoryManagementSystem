package com.inventoryManagementSystem.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
public class OrderHistoryController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order-history")
    public String showOrderHistoryPage(Model model) {
        model.addAttribute("title", "Order History");
        model.addAttribute("pageTitle", "History Page");
        return "orderHistory";
    }

    @GetMapping("/get-orders")
    @ResponseBody
    public List<Order> getOrders() {
        return orderService.getUniqueOrders();
    }

    @PostMapping("/update-order-status/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId) {
        // Call the service method to update all orders with the given order_id to "Paid"
        orderService.updateStatusToPaidForOrderId(orderId);
        return  ResponseEntity.ok("Order status updated successfully!");
    }


    @PostMapping("/delete-order/{orderId}")
public ResponseEntity<String> deleteOrder(@PathVariable String orderId) {
    // Call the service method to delete the order with the given order_id
    boolean isDeleted = orderService.deleteOrderByOrderId(orderId);
    if (isDeleted) {
        return ResponseEntity.ok("Order deleted successfully!");
    } else {
        return ResponseEntity.status(404).body("Order not found!");
    }
}

}
