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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Serve the customer page
    @GetMapping
    public String showCustomerPage(Model model) {
        model.addAttribute("title", "Customer");
        model.addAttribute("pageTitle", "Customer Page");
        return "customer"; // This should point to customer.html
    }

    // Save a new customer
    @PostMapping("/save-customer")
    public ResponseEntity<Map<String, String>> saveCustomer(@RequestBody Map<String, String> requestData) {
    
        String customerName = requestData.get("name");
        String customerEmail = requestData.get("email");
        String customerAddress = requestData.get("address");
        String customerPhone = requestData.get("phone");

        // Validate fields
        if (customerName == null || customerName.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Customer name cannot be empty");
            return ResponseEntity.badRequest().body(response);
        }

        // Create and save the customer
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setEmail(customerEmail);
        customer.setAddress(customerAddress);
        customer.setPhone(customerPhone);

        customerService.saveCustomer(customer);

        // Response back to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer saved successfully");
        return ResponseEntity.ok(response);
    }

    // Fetch all customers
    @GetMapping("/get-customers")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Update an existing customer
    @PutMapping("/update-customer/{id}")
    public ResponseEntity<Map<String, String>> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        customerService.updateCustomer(id, customerDetails);

        // Response back to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer updated successfully");
        return ResponseEntity.ok(response);
    }

    // Delete a customer by ID
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        // Response back to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer deleted successfully");
        return ResponseEntity.ok(response);
    }
}
