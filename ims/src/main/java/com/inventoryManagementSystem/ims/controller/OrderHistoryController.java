package com.inventoryManagementSystem.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.inventoryManagementSystem.ims.model.Customer;
import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.inventoryManagementSystem.ims.service.CustomerService;

@Controller
public class OrderHistoryController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/order-history")
    public String showOrderHistoryPage(Model model) {
        model.addAttribute("title", "Order History");
        model.addAttribute("pageTitle", "History Page");
        return "orderHistory";
    }

     
    @GetMapping("/invoice/{orderId}")
    public String showInvoicePage(@PathVariable String orderId, Model model) {
        model.addAttribute("orderId", orderId); // Pass the orderId to the template
        return "invoice"; // This should refer to `invoice.html` in the `templates` folder
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

@GetMapping("/invoice-data/{orderId}")
@ResponseBody
public Map<String, Object> getInvoiceData(@PathVariable String orderId) {
    List<Order> orders = orderService.getOrdersByOrderId(orderId);
    Map<String, Object> response = new HashMap<>();
    
    if (orders.isEmpty()) {
        response.put("error", "Order not found");
        return response;
    }

    Order sampleOrder = orders.get(0); // Assuming same customer for all items in the order
    Customer customer = customerService.getCustomerById(sampleOrder.getCustomer_id()).orElse(null);

    response.put("orderId", orderId);
    response.put("orders", orders);
    response.put("customer", customer);

    return response;
}



}
